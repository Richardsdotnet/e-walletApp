package com.richards.eWallet.utils;

import com.richards.eWallet.dto.request.RegistrationRequest;
import com.richards.eWallet.dto.response.RegistrationResponse;
import com.richards.eWallet.dto.response.TransactionResponse;
import com.richards.eWallet.models.Account;
import com.richards.eWallet.models.Transactions;

import java.math.BigDecimal;

public class Mapper {
    public static Account map(RegistrationRequest request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setEmail(request.getEmail());
        account.setPhoneNumber(request.getPhoneNumber());

        account.setAddress(request.getAddress());
        account.setDateOfBirth(request.getDateOfBirth());
        account.setPassword(request.getPassword());
        account.setUserName(request.getUserName());
        account.setBalance(new BigDecimal(0));
        return account;
    }

    public static RegistrationResponse map(Account account, RegistrationResponse registrationResponse) {
        registrationResponse.setUserName(account.getUserName());
        registrationResponse.setPassword(account.getPassword());
        return registrationResponse;
    }

    public static TransactionResponse map(Transactions transaction) {
        return TransactionResponse.builder()
                .message("Transaction Found")
                .senderAccountNumber(transaction.getSenderAccountNumber())
                .receiverAccountNumber(transaction.getReceiverAccountNumber())
                .id(transaction.getId())
                .build();
    }
}
