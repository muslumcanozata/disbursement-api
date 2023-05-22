package com.loan.disbursementapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO{
    private Integer id;
    private String firstName;
    private String lastName;
}
