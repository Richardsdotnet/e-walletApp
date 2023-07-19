package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.TransactionType;
import com.richards.eWallet.models.Transactions;
import com.richards.eWallet.repository.TransactionsRepository;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EwalletTransactionService implements TransactionService {

    private static final int PAGE_SIZE = 10;

private TransactionsRepository transactionsRepository;


    @Override
    public TransactionResponse save(TransactionRequest transactionRequest) {
        return null;
    }

    public List<Transactions> transactions(int page) {
            int startIndex = (page - 1) * PAGE_SIZE;
            List<Transactions> allTransactions = transactionsRepository.findAll();
            return allTransactions.subList(startIndex, Math.min(startIndex + PAGE_SIZE, allTransactions.size()));
        }

    @Override
    public Optional<Object> findAllTransactionsInBatchesOf(int number) {
        return Optional.empty();
    }

    public Optional<TransactionResponse> findAllTransactions(TransactionRequest transactionRequest, int page) {
            List<Transactions> paginatedTransactions = transactions(page);
            return Optional.empty();
    }

    public Optional<Object> getCreditRecordsRelatedToUserWith(String username) {
        return Optional.empty();
    }

    public Optional<Object> getDebitRecordsRelatedToUserWith(String username) {
        return  Optional.empty();
    }

    @Override
    public Optional<Object> findAllTransactionsByDate(LocalDateTime transactionDate, int page) {
        return Optional.empty();
    }

    @Override
    public Optional<Object> getTransactionsByTransaction(String transactionType) {
        TransactionResponse transactionResponse = new TransactionResponse();
        List<TransactionResponse> responseList =new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        Optional<List<Transactions>> foundTransactions = transactionsRepository.findTransactionsByType(TransactionType.valueOf(transactionType.toUpperCase()));
        foundTransactions
                .ifPresentOrElse(listOfTransactions -> {
                    listOfTransactions
                            .forEach(transaction -> {
                                mapper.map(transaction, transactionResponse);
                                responseList.add(transactionResponse);
                            });
                }, ()->{
                    throw new RuntimeException("No Transaction Of Type " +transactionType.toUpperCase());
                });
        return Optional.of(responseList);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return null;
    }

    private Transactions allTransactionByDate(LocalDateTime localDateTime) {
            return transactionsRepository.findTransactionsByDate(localDateTime);
    }

    /*Transactions transactionsByDate = transactionByDate(transactionRequest.getLocalDateTime());
    int startIndex = (page - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, allTransactionByDate(transactionRequest.getLocalDateTime()).size());
        List<Transactions> paginatedTransactions = transactionsByDate.subList(startIndex, Math.min(startIndex + PAGE_SIZE, transactionsByDate.size()));
        return new TransactionResponse(paginatedTransactions);
    }
*/

}


