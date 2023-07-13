package com.richards.eWallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositResponse {
    private BigDecimal balance;
    private String message;
}
