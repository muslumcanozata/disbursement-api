package com.loan.disbursementapi.mapper;

import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.controller.response.InstallmentResponse;
import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoreMapperImpl implements CoreMapper {
    @Override
    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUpdatedAt(user.getUpdatedAt());
        user.setCreatedAt(user.getCreatedAt());
        return user;
    }

    @Override
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    @Override
    public List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public List<User> toUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(this::toUser).collect(Collectors.toList());
    }

    @Override
    public User toUserFromCreateUserRequest(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        return user;
    }

    @Override
    public List<User> toUserFromListOfCreateUserRequest(List<CreateUserRequest> createUserRequests) {
        List<User> users = new ArrayList<>();
        createUserRequests.forEach(request -> users.add(toUserFromCreateUserRequest(request)));
        return users;
    }

    @Override
    public InstallmentResponse toInstallmentResponse(InstallmentDTO installmentDTO) {
        InstallmentResponse installmentResponse = new InstallmentResponse();
        installmentResponse.setId(installmentDTO.getId());
        installmentResponse.setAmount(installmentDTO.getAmount());
        installmentResponse.setDueDate(installmentDTO.getDueDate());
        return installmentResponse;
    }

    @Override
    public Installment toInstallment(InstallmentDTO installmentDTO) {
        Installment installment = new Installment();
        installment.setId(installmentDTO.getId());
        installment.setLast(installmentDTO.isLast());
        installment.setStatus(installmentDTO.getStatus());
        installment.setDueDate(installmentDTO.getDueDate());
        installment.setUpdatedAt(installmentDTO.getUpdatedAt());
        installment.setCreatedAt(installmentDTO.getCreatedAt());
        installment.setCredit(toCredit(installmentDTO.getCreditDTO()));
        installment.setAmount(installmentDTO.getAmount());
        return installment;
    }

    @Override
    public InstallmentDTO toInstallmentDTO(Installment installment) {
        InstallmentDTO installmentDTO = new InstallmentDTO();
        installmentDTO.setId(installment.getId());
        installmentDTO.setLast(installment.isLast());
        installmentDTO.setStatus(installment.getStatus());
        installmentDTO.setDueDate(installment.getDueDate());
        installmentDTO.setUpdatedAt(installment.getUpdatedAt());
        installmentDTO.setCreatedAt(installment.getCreatedAt());
        installmentDTO.setAmount(installment.getAmount());
        return installmentDTO;
    }

    @Override
    public List<Installment> toInstallments(List<InstallmentDTO> installmentDTOs) {
        return installmentDTOs.stream().map(this::toInstallment).collect(Collectors.toList());
    }

    @Override
    public List<InstallmentDTO> toInstallmentDTOs(List<Installment> installments) {
        return installments.stream().map(this::toInstallmentDTO).collect(Collectors.toList());
    }

    @Override
    public Credit toCredit(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setId(creditDTO.getId());
        credit.setAmount(creditDTO.getAmount());
        credit.setCreatedAt(creditDTO.getCreatedAt());
        credit.setUpdatedAt(creditDTO.getUpdatedAt());
        credit.setStatus(creditDTO.getStatus());
        return credit;
    }

    @Override
    public CreditDTO toCreditDTO(Credit credit) {
        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setId(credit.getId());
        creditDTO.setAmount(credit.getAmount());
        creditDTO.setCreatedAt(credit.getCreatedAt());
        creditDTO.setUpdatedAt(credit.getUpdatedAt());
        creditDTO.setStatus(credit.getStatus());
        return creditDTO;
    }

    @Override
    public List<Credit> toCredits(List<CreditDTO> creditDTOs) {
        return creditDTOs.stream().map(this::toCredit).collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> toCreditDTOs(List<Credit> credits) {
        return credits.stream().map(this::toCreditDTO).collect(Collectors.toList());
    }
}
