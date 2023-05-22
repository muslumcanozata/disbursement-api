package com.loan.disbursementapi.domain.dto;

import com.loan.disbursementapi.domain.enums.InstallmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InstallmentDTO extends BaseDTO{
    private Integer id;
    private BigDecimal amount;
    private CreditDTO creditDTO;
    private boolean isLast;
    private InstallmentStatus status;
    private LocalDate dueDate;
}