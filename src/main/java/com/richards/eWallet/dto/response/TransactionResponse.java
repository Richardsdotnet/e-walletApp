package com.richards.eWallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String id;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private String message;
}
