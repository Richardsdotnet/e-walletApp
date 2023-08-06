package com.richards.eWallet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter


public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private  LocalDateTime transactionDate;

}
