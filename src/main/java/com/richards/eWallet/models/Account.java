package com.richards.eWallet.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.math.BigDecimal;


@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    @Id
    private String id;
    @Email(message = "Please enter a valid email address")
    private String email;
    private String dateOfBirth;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    private String password;
    @NonNull
    private BigDecimal balance;
    private String accountNumber;
    private String Address;
    private String pin;
}
