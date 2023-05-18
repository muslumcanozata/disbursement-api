package com.loan.disbursementapi.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
}
