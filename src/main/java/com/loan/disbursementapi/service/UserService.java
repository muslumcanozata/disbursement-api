package com.loan.disbursementapi.service;

import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.domain.entity.User;

import java.util.List;

public interface UserService {
    UserDTO insertOne(CreateUserRequest createUserRequest);

    List<UserDTO> insertAll(List<CreateUserRequest> createUserRequests);

    UserDTO getOne(int id);

    List<UserDTO> getAll();

    User getUser(int id);

    void deleteOne(int id);
}
