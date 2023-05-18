package com.loan.disbursementapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DisbursementRequest {
    private int userId;
    private BigDecimal amount;
    private int installmentCount;
}
