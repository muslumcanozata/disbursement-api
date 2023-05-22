package com.loan.disbursementapi.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InstallmentResponse {
    private int id;
    private LocalDate dueDate;
    private BigDecimal amount;
}
