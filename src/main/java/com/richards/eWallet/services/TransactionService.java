package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.models.Transactions;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TransactionService {

     List<Transactions> transactions(int page);
    TransactionResponse findAllTransactions(TransactionRequest transactionRequest, int page);
    TransactionResponse credit(TransactionRequest transactionRequest);
    TransactionResponse debit(TransactionRequest transactionRequest);
    LocalDateTime findTransactionByDate(LocalDateTime transactionDate, int page);
    List<Transactions> getAllTransactions();


//    List<Transactions> transactions();
//    TransactionResponse findAllTransactions(TransactionRequest transactionRequest);
//    TransactionResponse credit(TransactionRequest transactionRequest);
//    TransactionResponse debit(TransactionRequest transactionRequest);
//    LocalDateTime findTransactionByDate(LocalDateTime transactionDate);




}
