package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.models.Transactions;
import com.richards.eWallet.repository.TransactionsRepository;

public class EwalletTransactionService implements TransactionService {

private TransactionsRepository transactionsRepository;

    @Override
    public Transactions findByTransactionStatus(TransactionRequest transactionRequest) {
        return transactionsRepository.findTransactionsByStatus();
    }
}
