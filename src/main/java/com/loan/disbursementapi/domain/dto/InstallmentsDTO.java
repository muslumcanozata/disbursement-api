package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class InstallmentsDTO {
    private Integer id;
    private BigDecimal amount;
    private CreditDTO credit;
    private Integer status;
}