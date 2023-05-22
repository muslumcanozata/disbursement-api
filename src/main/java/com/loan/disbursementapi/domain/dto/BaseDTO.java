package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaseDTO {
    private LocalDate updatedAt;
    private LocalDate createdAt;
}
