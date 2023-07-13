package com.richards.eWallet.dto.response;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class RegistrationResponse {

    String message;
    String userName;
    String password;

}
