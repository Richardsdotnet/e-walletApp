package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.Transactions;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {

    TransactionResponse save(TransactionRequest transactionRequest);
    List<Transactions> transactions(int page);
    Optional<Object> findAllTransactionsInBatchesOf(int number);
    Optional<Object> getCreditRecordsRelatedToUserWith(String username);
    Optional<Object> getDebitRecordsRelatedToUserWith(String username);
    Optional<Object> findAllTransactionsByDate(LocalDateTime transactionDate, int page);
    Optional<Object> getTransactionsByTransaction(String transactionType);
    List<Transactions> getAllTransactions();




}
