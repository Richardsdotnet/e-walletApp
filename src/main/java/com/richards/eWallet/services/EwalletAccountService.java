package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.*;
import com.richards.eWallet.dto.response.*;
import com.richards.eWallet.models.Account;
import com.richards.eWallet.repository.AccountsRepository;
import com.richards.eWallet.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor

//@NoArgsConstructor
public class EwalletAccountService implements AccountService {

    //@Autowired
    private final AccountsRepository accountsRepository;



    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        Account account = Mapper.map(registrationRequest);
        String accountNumber = generateAccountNumber(account.getPhoneNumber());
        account.setAccountNumber((accountNumber));

        Account saveAccount = accountsRepository.save(account);

        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("Registration Successful");
        return Mapper.map(saveAccount, response);
    }


    private String generateAccountNumber(String phoneNumber){
        StringBuilder generatedAccountNumber = new StringBuilder();
        for (int i = 1; i < phoneNumber.length(); i++) {
            generatedAccountNumber.append(phoneNumber.charAt(i));
        }
        return generatedAccountNumber.toString();
    }


    @Override
    public Account findByAccountNumber(String phoneNumber){
        return accountsRepository.findByAccountNumber(phoneNumber);
    }



    public LoginResponse login(LoginRequest loginRequest) {
        String password = loginRequest.getPassword();
        String userName = loginRequest.getUserName();
        LoginResponse loginResponse =
        authenticateLoginDetails(userName, password);
        loginResponse.setMessage("Login Successful");
        loginResponse.setLoggedIn(true);
        return loginResponse;
    }


    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        Account foundAcc = accountsRepository.findByAccountNumber(depositRequest.getAccountNumber());
        DepositResponse depositResponse = new DepositResponse();

        if (foundAcc != null) {

           // depositToAccount();

                BigDecimal currentBalance = foundAcc.getBalance();
                BigDecimal depositAmount = depositRequest.getAmount();
                BigDecimal newBalance = currentBalance.add( depositAmount);

                foundAcc.setBalance(newBalance);

                accountsRepository.save(foundAcc);
            depositResponse.setMessage("Deposit successful");
            return depositResponse;

        }
        depositResponse.setMessage("Account not found");
        return depositResponse;
    }

    @Override
    public WithdrawalResponse withdraw(WithdrawalRequest withdrawalRequest) {
        Account foundAccount = accountsRepository.findByAccountNumber(withdrawalRequest.getAccountNumber());
        WithdrawalResponse withdrawalResponse = new WithdrawalResponse();
        if (foundAccount != null){
            BigDecimal currentBalance = foundAccount.getBalance();
            BigDecimal withdrawalAmount = withdrawalRequest.getAmount();
                if (currentBalance.compareTo(withdrawalAmount) >= 0){
                    BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);
                    foundAccount.setBalance(newBalance);
                    accountsRepository.save(foundAccount);
                    withdrawalResponse.setMessage("Withdrawal successful");

                    return withdrawalResponse;
                }
                withdrawalResponse.setMessage("Insufficient Balance");
                return withdrawalResponse;
        }
        withdrawalResponse.setMessage("Account not found");
        return withdrawalResponse;
    }

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
    Account senderAccount = accountsRepository.findByAccountNumber(transferRequest.getSenderAccount());
    Account receiverAccount = accountsRepository.findByAccountNumber(transferRequest.getReceiverAccount());

    TransferResponse transferResponse = new TransferResponse();

    if(senderAccount != null && receiverAccount != null){
        BigDecimal senderAccountBalance = senderAccount.getBalance();
        BigDecimal amountToSend = BigDecimal.valueOf(0);
        if (senderAccountBalance.compareTo( amountToSend) >= 0){

            BigDecimal currentBalance = senderAccount.getBalance();
            BigDecimal newBalance = currentBalance.subtract(transferRequest.getAmount());
            senderAccount.setBalance(newBalance);
            senderAccount.setBalance(newBalance);

            receiverAccount.setBalance(transferRequest.getAmount());
            accountsRepository.save(senderAccount);
            accountsRepository.save(receiverAccount);

            transferResponse.setMessage("Transfer successful");
            return transferResponse;
        }
        transferResponse.setMessage("Insufficient funds");
        return transferResponse;
    }
        transferResponse.setMessage("Account Not found");
        return transferResponse;
    }


    private LoginResponse authenticateLoginDetails(String userName, String password) {
        LoginResponse loginResponse = new LoginResponse();
        Account foundAccount = accountsRepository.findByUserName(userName);
        if (foundAccount.getPassword().equals(password)&&foundAccount.getUserName().equals(userName)) {
            loginResponse.setMessage("Login Successful");
            loginResponse.setLoggedIn(true);
        }
        else {
            loginResponse.setLoggedIn(false);
            loginResponse.setMessage("Incorrect username or password");
        }
        return loginResponse;

    }



    @Override
    public BigDecimal getBalance(String accountNumber) {
        return null;
    }

    @Override
    public List<Account> accounts() {
        return accountsRepository.findAll();
    }

    @Override
    public void deleteAll() {
        accountsRepository.deleteAll();
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        Account foundAcc = accountsRepository.findByAccountNumber(accountNumber);
        if (foundAcc != null)
            return foundAcc.getBalance();
        //else throw accountNotFoundException
        return null;
    }

}
