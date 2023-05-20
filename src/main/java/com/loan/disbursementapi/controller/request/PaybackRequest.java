package com.loan.disbursementapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaybackRequest {
    private Integer installmentId;
    private BigDecimal amount;
}
