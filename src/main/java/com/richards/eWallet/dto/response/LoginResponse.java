package com.richards.eWallet.dto.response;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {

     String message;
     String userName;
     String password;
     boolean isLoggedIn;
}
