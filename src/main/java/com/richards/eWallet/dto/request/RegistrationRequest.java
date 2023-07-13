package com.richards.eWallet.dto.request;

import com.richards.eWallet.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor


public class RegistrationRequest {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String id;
    private String email;
    private String dateOfBirth;
    private String userName;
    private String password;
    private BigDecimal balance;
    private Account accountNumber;
    private String Address;
    private String pin;
}
