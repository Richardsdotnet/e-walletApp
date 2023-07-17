package com.richards.eWallet;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.models.TransactionType;
import com.richards.eWallet.models.Transactions;
import com.richards.eWallet.services.EwalletTransactionService;
import com.richards.eWallet.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.richards.eWallet.models.TransactionStatus.SUCCESSFUL;
import static com.richards.eWallet.models.TransactionType.CREDIT;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RequiredArgsConstructor
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;

    private TransactionRequest transactionRequest;
    private Transactions transactionResponse;
    private TransactionStatus transactionStatus;


    @BeforeEach
    void setUp(){
        transactionService = new EwalletTransactionService();
        transactionRequest = new TransactionRequest();
       // transactionResponse = new TransactionResponse();

        transactionRequest = TransactionRequest.builder()
                .type(CREDIT)
                .amount(new BigDecimal("200"))
                .status(SUCCESSFUL)
                .senderAccountNumber("8144263789")
                .receiverAccountNumber("8081493711")
                .localDateTime(LocalDateTime.now())
                .build();

    }
    @Test
    void findTransactionsByType(Transactions transactionType){
//        Transactions transactions= new Transactions();
//        transactions.setType(transactionType.getType());
        transactionService.getTransactionsByTransaction(transactionType.getType().toString());
        assertEquals(CREDIT,transactionType.getType() );



    }

}
