package com.richards.eWallet.repository;

import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.models.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionsRepository extends MongoRepository<Transactions, String> {
    TransactionStatus findTransactionsByStatus(TransactionStatus status);
}
