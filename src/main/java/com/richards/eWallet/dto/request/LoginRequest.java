package com.richards.eWallet.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor


public class LoginRequest {
    @Autowired
    private String userName;
    private String password;
    private boolean isLoggedIn;
}
