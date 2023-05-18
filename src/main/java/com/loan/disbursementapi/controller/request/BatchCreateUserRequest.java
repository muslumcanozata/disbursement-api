package com.loan.disbursementapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchCreateUserRequest {
    private List<CreateUserRequest> createUserRequests;
}
