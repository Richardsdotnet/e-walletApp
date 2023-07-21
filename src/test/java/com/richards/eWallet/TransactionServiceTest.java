package com.richards.eWallet;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.repository.TransactionsRepository;
import com.richards.eWallet.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.richards.eWallet.models.TransactionStatus.SUCCESSFUL;
import static com.richards.eWallet.models.TransactionType.CREDIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Repository
@SpringBootTest



public class TransactionServiceTest {

    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private TransactionService transactionService;

    private TransactionResponse transactionResponse;
    private TransactionStatus transactionStatus;


    @BeforeEach
    void setUp(){

        TransactionRequest transactionRequest = new TransactionRequest();

        transactionRequest = TransactionRequest.builder()
                .type(CREDIT)
                .amount(new BigDecimal("200"))
                .status(SUCCESSFUL)
                .senderAccountNumber("8144263789")
                .receiverAccountNumber("8081493711")
                .transactionDate(LocalDateTime.now())
                .build();

    }
    @Test
    void findTransactionsByType(){
//        Transactions transactions= new Transactions();
//        transactions.setType(transactionType.getType());
        Optional<Object> transactions = transactionService.getTransactionsByTransaction(CREDIT.toString());
//        assertEquals(CREDIT, );

    }
    @Test
    void testSaveTransaction(){
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .type(CREDIT)
                .amount(new BigDecimal("200"))
                .status(SUCCESSFUL)
                .senderAccountNumber("8144263789")
                .receiverAccountNumber("8081493711")
                .transactionDate(LocalDateTime.now())
                .build();
        TransactionResponse transactionResponse1 = transactionService.saveTransaction(transactionRequest);
        assertEquals(1, transactionService.getAllTransactions().size());
        assertEquals("Transaction saved", transactionResponse1.getMessage());
    }

}
