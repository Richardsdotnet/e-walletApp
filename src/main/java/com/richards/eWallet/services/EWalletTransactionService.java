package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.TransactionRequest;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.TransactionStatus;
import com.richards.eWallet.models.TransactionType;
import com.richards.eWallet.models.Transactions;
import com.richards.eWallet.repository.TransactionsRepository;
import com.richards.eWallet.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j



public class EWalletTransactionService implements TransactionService {

    private final TransactionsRepository transactionsRepository;
    private static final int PAGE_SIZE = 10;






    @Override
    public TransactionResponse saveTransaction(TransactionRequest transactionRequest) {
        Transactions transaction = new Transactions();
         String senderAccountNumber = transactionRequest.getSenderAccountNumber();
         String receiverAccountNumber = transactionRequest.getReceiverAccountNumber();
         BigDecimal amount = transactionRequest.getAmount();
         TransactionType type = transactionRequest.getType();
         TransactionStatus status = transactionRequest.getStatus();
         LocalDateTime transactionDate = transactionRequest.getTransactionDate();

         transaction.setSenderAccountNumber(senderAccountNumber);
         transaction.setReceiverAccountNumber(receiverAccountNumber);
         transaction.setType(type);
         transaction.setAmount(amount);
         transaction.setStatus(status);
         transaction.setTransactionDate(transactionDate);

         Transactions savedTransaction = transactionsRepository.save(transaction);
         log.info("Transaction details -> {}", savedTransaction);

         TransactionResponse transactionResponse = new TransactionResponse();
         transactionResponse.setMessage("Transaction saved");
         return transactionResponse;



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
//        TransactionResponse transactionResponse = new TransactionResponse();
//        List<TransactionResponse> responseList =new ArrayList<>();
//        ModelMapper mapper = new ModelMapper();
//        List<Transactions> foundTransactions = transactionsRepository.findTransactionsByType(TransactionType.valueOf(transactionType.toUpperCase()));
//        foundTransactions
//                (listOfTransactions -> {
//                    listOfTransactions
//                            .forEach(transaction -> {
//                                mapper.map(transaction, transactionResponse);
//                                responseList.add(transactionResponse);
//                            });
//                }, ()->{
//                    throw new RuntimeException("No Transaction Of Type " +transactionType.toUpperCase());
//                });
//        return Optional.of(responseList);
    return Optional.empty();}

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<TransactionResponse> responses = new ArrayList<>();
        List<Transactions> foundTransactions = transactionsRepository.findAll();
        if(foundTransactions.stream().findAny().isPresent()){
            foundTransactions.forEach(transactions -> {
                TransactionResponse mappedTransaction = Mapper.map(transactions);
                responses.add(mappedTransaction);
            });
            return responses;
        }
       throw new RuntimeException();
    }


    private List<Transactions> allTransactionByDate(LocalDateTime localDateTime) {
            return transactionsRepository.findTransactionsByTransactionDate(localDateTime);
    }

    /*Transactions transactionsByDate = transactionByDate(transactionRequest.getLocalDateTime());
    int startIndex = (page - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, allTransactionByDate(transactionRequest.getLocalDateTime()).size());
        List<Transactions> paginatedTransactions = transactionsByDate.subList(startIndex, Math.min(startIndex + PAGE_SIZE, transactionsByDate.size()));
        return new TransactionResponse(paginatedTransactions);
    }
*/

}


