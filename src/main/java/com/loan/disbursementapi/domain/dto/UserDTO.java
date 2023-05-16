package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private List<CreditDTO> credits;
}
