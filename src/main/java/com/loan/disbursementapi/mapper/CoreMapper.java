package com.loan.disbursementapi.mapper;

import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.controller.response.InstallmentResponse;
import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.entity.User;

import java.util.List;

public interface CoreMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    List<UserDTO> toUserDTOs(List<User> users);
    List<User> toUsers(List<UserDTO> userDTOs);
    User toUserFromCreateUserRequest(CreateUserRequest createUserRequest);
    List<User> toUserFromListOfCreateUserRequest(List<CreateUserRequest> createUserRequests);

    InstallmentResponse toInstallmentResponse(InstallmentDTO installmentDTO);
    Installment toInstallment(InstallmentDTO installmentDTO);
    InstallmentDTO toInstallmentDTO(Installment installment);
    List<Installment> toInstallments(List<InstallmentDTO> installmentDTOs);
    List<InstallmentDTO> toInstallmentDTOs(List<Installment> installments);

    Credit toCredit(CreditDTO creditDTO);
    CreditDTO toCreditDTO(Credit credit);
    List<Credit> toCredits(List<CreditDTO> creditDTOs);
    List<CreditDTO> toCreditDTOs(List<Credit> credits);
}
