package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDTO {
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
