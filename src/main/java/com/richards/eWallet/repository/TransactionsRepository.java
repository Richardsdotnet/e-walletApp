package com.richards.eWallet.repository;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.models.TransactionType;
import com.richards.eWallet.models.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends MongoRepository<Transactions, String> {
    List<Transactions> findTransactionsByStatus(TransactionStatus status);

    List<Transactions> findTransactionsByType(TransactionType type);
    List<Transactions> findTransactionsByTransactionDate(LocalDateTime localDateTime);
}



