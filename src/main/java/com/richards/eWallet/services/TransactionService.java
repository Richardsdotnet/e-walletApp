package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.models.Transactions;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
//    List<Transactions> transactions();
//    TransactionResponse findAllTransactions(TransactionRequest transactionRequest);
//    TransactionResponse credit(TransactionRequest transactionRequest);
//    TransactionResponse debit(TransactionRequest transactionRequest);
//    LocalDateTime findTransactionByDate(LocalDateTime transactionDate);



    Transactions findByTransactionStatus(TransactionRequest transactionRequest);
}
