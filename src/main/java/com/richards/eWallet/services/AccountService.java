package com.richards.eWallet.services;

import com.richards.eWallet.dto.request.*;
import com.richards.eWallet.dto.response.*;
import com.richards.eWallet.models.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
 RegistrationResponse register(RegistrationRequest registrationRequest);
 LoginResponse login(LoginRequest loginRequest);

 DepositResponse deposit(DepositRequest depositResponse);

 WithdrawalResponse withdraw(WithdrawalRequest withdrawalRequest);

 TransferResponse transfer(TransferRequest transferRequest);

 BigDecimal getBalance(String accountNumber);

 List<Account> accounts();

 void deleteAll();

 Account findByAccountNumber(String accountNumber);
 BigDecimal checkBalance(String accountNumber);
}
