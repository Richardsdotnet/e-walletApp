package com.richards.eWallet;

import com.richards.eWallet.dto.request.*;
import com.richards.eWallet.dto.response.*;
import com.richards.eWallet.models.Account;
import com.richards.eWallet.services.AccountService;
import com.richards.eWallet.services.EwalletAccountService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
class EwalletAccountServiceTest {
    @Autowired
    EwalletAccountService ewalletAccountService;

    RegistrationRequest registrationRequest;
    RegistrationResponse registrationResponse;
    LoginRequest loginRequest;
    LoginResponse loginResponse;
    WithdrawalRequest withdrawalRequest;
    WithdrawalResponse withdrawalResponse;
    TransferRequest transferRequest;
    TransferResponse transferResponse;
    @Autowired
    AccountService accountService;

    DepositRequest depositRequest;
    DepositResponse depositResponse;

    @BeforeEach
    void setUp() {
        ewalletAccountService.deleteAll();
        registrationResponse = new RegistrationResponse();
        registrationRequest = new RegistrationRequest();
        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .firstName("Chris")
                .lastName("Rich")
                .email("rich@gmail.com")
                .dateOfBirth("25th,April, 2000")
                .userName("Rich123")
                .password("1234")
                .Address("Semicolon")
                .phoneNumber("08144263789")
                .build();

        registrationResponse = ewalletAccountService.register(registrationRequest);

    }


    @Test
    void registerAccount() {
        assertEquals("Registration Successful", registrationResponse.getMessage());
        assertEquals(BigInteger.ONE.intValue(), ewalletAccountService.accounts().size());
    }

    @Test
    void loginRegisteredAccount() {
        LoginRequest loginRequest = LoginRequest.builder()
                .userName("Rich123")
                .password("1234")
                .build();
        loginResponse = ewalletAccountService.login(loginRequest);
      //  System.out.println(loginResponse);
        assertEquals("Login Successful", loginResponse.getMessage());
        assertTrue(loginResponse.isLoggedIn());

    }

    @Test
    void depositToAccount() {
        depositRequest = new DepositRequest();
        depositResponse = new DepositResponse();

        DepositRequest depositRequest = DepositRequest.builder()
                .amount(new BigDecimal("1000"))
                .accountNumber("8144263789")
                .build();
        DepositResponse depositResponse = accountService.deposit(depositRequest);
        BigDecimal balance = accountService.checkBalance(depositRequest.getAccountNumber());
        assertEquals("Deposit successful", depositResponse.getMessage());
        assertEquals(new BigDecimal(1000), balance);
    }


    @Test

    void withdrawFromAccount(){
        withdrawalRequest = new WithdrawalRequest();
        withdrawalResponse = new WithdrawalResponse();
        DepositRequest depositRequest = DepositRequest.builder()
                .amount(new BigDecimal("1000"))
                .accountNumber("8144263789")
                .build();
        accountService.deposit(depositRequest);

        WithdrawalRequest withdrawalRequest = WithdrawalRequest.builder()
                .accountNumber("8144263789")
                .amount(new BigDecimal(500))
                .build();
        WithdrawalResponse registrationResponse = accountService.withdraw(withdrawalRequest);
        BigDecimal balance = accountService.checkBalance(withdrawalRequest.getAccountNumber());
        assertEquals(new BigDecimal(500), balance);
        assertEquals("Withdrawal successful", registrationResponse.getMessage());


    }
    @Test
    public void testThatTwoObjectsCanBeSaved(){
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .firstName("Chris")
                .lastName("Rich")
                .email("rich@gmail.com")
                .dateOfBirth("25th,April, 2000")
                .userName("Rich123")
                .password("1234")
                .Address("Semicolon")
                .phoneNumber("08144263789")
                .build();

        registrationResponse = ewalletAccountService.register(registrationRequest);
        assertEquals(2 , accountService.accounts().size());
    }

    @Test

    void transferBetweenTwoSavedAccounts(){
        transferRequest = new TransferRequest();
        transferResponse = new TransferResponse();

        registrationResponse = new RegistrationResponse();
        registrationRequest = new RegistrationRequest();

        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .firstName("Ceph")
                .lastName("Ayo")
                .email("ceph@gmail.com")
                .dateOfBirth("25th,April, 1900")
                .userName("Ceph123")
                .password("1234")
                .Address("Semicolon")
                .phoneNumber("08081493711")
                .build();

        registrationResponse = ewalletAccountService.register(registrationRequest);

        depositRequest = new DepositRequest();
        depositResponse = new DepositResponse();

        DepositRequest depositRequest = DepositRequest.builder()
                .amount(new BigDecimal("1000"))
                .accountNumber("8144263789")
                .build();
        DepositResponse depositResponse = accountService.deposit(depositRequest);
        assertEquals(2 , accountService.accounts().size());

        TransferRequest transferRequest = TransferRequest.builder()
                .senderAccount("8144263789")
                .receiverAccount("8081493711")
                .amount(new BigDecimal("300"))
                .senderPin("1234")
                .build();

        TransferResponse transferResponse = accountService.transfer(transferRequest);

        Account receiverAccount = accountService.findByAccountNumber(transferRequest.getReceiverAccount());
        Account senderAccount = accountService.findByAccountNumber(transferRequest.getSenderAccount());

        assertEquals("Transfer successful", transferResponse.getMessage());
        assertEquals(new BigDecimal("700"), senderAccount.getBalance());

        assertEquals(new BigDecimal("300"), receiverAccount.getBalance());
        System.out.println(receiverAccount);

    }
}


