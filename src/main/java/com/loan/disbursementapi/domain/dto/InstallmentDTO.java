package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
public class InstallmentDTO extends BaseDTO{
    private Integer id;
    private BigDecimal amount;
    private CreditDTO credit;
    private Integer status;
    private LocalDateTime dueDate;
}