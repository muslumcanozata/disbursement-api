package com.loan.disbursementapi.domain.dto;

import com.loan.disbursementapi.domain.enums.CreditStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDTO extends BaseDTO{
    private Integer id;
    private CreditStatus status;
    private BigDecimal amount;
}