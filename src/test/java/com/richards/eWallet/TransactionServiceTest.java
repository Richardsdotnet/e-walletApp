package com.richards.eWallet;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RequiredArgsConstructor
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;

    private TransactionRequest transactionRequest;
    private Transactions transactionResponse;


    @BeforeEach
    void setUp(){
        transactionService = new EwalletTransactionService();
        transactionRequest = new TransactionRequest();
       // transactionResponse = new TransactionResponse();

        transactionRequest = TransactionRequest.builder()
                .type(TransactionType.CREDIT)
                .amount(new BigDecimal("200"))
                .status(TransactionStatus.SUCCESSFUL)
                .senderAccountNumber("8144263789")
                .receiverAccountNumber("8081493711")
                .localDateTime(LocalDateTime.now())
                .build();

    }
    @Test
    void findTransactionsByStatus(){
        Transactions transactions = new Transactions();
        transactionRequest = TransactionRequest.builder()
                .status(TransactionStatus.SUCCESSFUL)
                .build();
        Transactions status = transactionService.findByTransactionStatus(transactionRequest);
       // assertEquals( transactions.getStatus() );



    }

}
