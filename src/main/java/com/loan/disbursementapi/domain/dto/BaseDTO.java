package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BaseDTO {
    private Timestamp updatedAt;
    private Timestamp createdAt;
}
