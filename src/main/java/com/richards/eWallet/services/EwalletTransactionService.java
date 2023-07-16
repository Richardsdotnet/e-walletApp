package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.models.Transactions;
import com.richards.eWallet.repository.TransactionsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EwalletTransactionService implements TransactionService {

    private static final int PAGE_SIZE = 10;

private TransactionsRepository transactionsRepository;

        public List<Transactions> transactions(int page) {
            // Calculate the starting index based on the page number and page size
            int startIndex = (page - 1) * PAGE_SIZE;
            // Call the data access layer to retrieve the transactions from the starting index
            List<Transactions> allTransactions = getAllTransactions(); // Replace this with your data access layer or service method to retrieve all transactions
            // Extract the subset of transactions based on the starting index and page size
            List<Transactions> paginatedTransactions = allTransactions.subList(startIndex, Math.min(startIndex + PAGE_SIZE, allTransactions.size()));
            return paginatedTransactions;
        }

        public TransactionResponse findAllTransactions(TransactionRequest transactionRequest, int page) {
            List<Transactions> paginatedTransactions = transactions(page);
            return new TransactionResponse(paginatedTransactions);
        }

        public TransactionResponse credit(TransactionRequest transactionRequest) {
            // Credit transaction logic goes here
        }

        public TransactionResponse debit(TransactionRequest transactionRequest) {
            // Debit transaction logic goes here
        return  null;
        }

        public LocalDateTime findTransactionByDate(LocalDateTime transactionDate, int page) {
            // Call the data access layer or service method to find transactions by date
            List<Transactions> transactionsByDate = findTransactionsByDate(transactionDate); // Replace this with your data access layer or service method
            // Calculate the starting index based on the page number and page size
            int startIndex = (page - 1) * PAGE_SIZE;
            // Extract the subset of transactions based on the starting index and page size
            List<Transactions> paginatedTransactions = transactionsByDate.subList(startIndex, Math.min(startIndex + PAGE_SIZE, transactionsByDate.size()));
            return new TransactionResponse(paginatedTransactions);
        }

        // Dummy method to simulate retrieving all transactions
        private List<Transactions> getAllTransactions() {
            List<Transactions> allTransactions = new ArrayList<>();
            // Add logic to retrieve all transactions from data source
            // ...
            return allTransactions;
        }

        // Dummy method to simulate finding transactions by date
        private List<Transactions> findTransactionsByDate(LocalDateTime transactionDate) {
            List<Transactions> transactionsByDate = new ArrayList<>();
            // Add logic to find transactions by date from data source
            // ...
            return transactionsByDate;
        }
    }


