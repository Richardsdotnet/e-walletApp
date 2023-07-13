package com.richards.eWallet.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;


@Builder
@Document
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    @Id
    private String id;
    private String email;
    private String dateOfBirth;
    @NonNull
    private String userName;
    private String password;
    @NonNull
    private BigDecimal balance;
    private String accountNumber;
    private String Address;
    private String pin;

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", Address='" + Address + '\'' +
                ",first name "+ firstName+

                '}';
    }


}
