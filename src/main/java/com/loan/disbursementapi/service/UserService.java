package com.loan.disbursementapi.service;

import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO insertOne(CreateUserRequest createUserRequest);

    List<UserDTO> insertAll(List<CreateUserRequest> createUserRequests);

    UserDTO getOne(int id);

    List<UserDTO> getAll();

    void deleteOne(int id);
}
