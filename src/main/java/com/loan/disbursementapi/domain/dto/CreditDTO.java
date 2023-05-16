package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDTO {
    private Integer id;
    private Integer status;
    private BigDecimal amount;
    private UserDTO user;
}