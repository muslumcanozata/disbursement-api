package com.loan.disbursementapi.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InstallmentResponse {
    private int id;
    private LocalDateTime dueDate;
    private BigDecimal amount;
}
