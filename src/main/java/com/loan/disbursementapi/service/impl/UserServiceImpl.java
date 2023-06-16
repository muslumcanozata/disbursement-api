package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.exception.custom.UserNotFoundException;
import com.loan.disbursementapi.mapper.CoreMapper;
import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.dao.UserRepository;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CoreMapper coreMapper;

    @Override
    public UserDTO insertOne(CreateUserRequest createUserRequest) {
        User user = coreMapper.toUserFromCreateUserRequest(createUserRequest);
        User inserted = userRepository.save(user);
        return coreMapper.toUserDTO(inserted);
    }

    @Override
    public List<UserDTO> insertAll(List<CreateUserRequest> createUserRequests) {
        List<User> users = coreMapper.toUserFromListOfCreateUserRequest(createUserRequests);
        List<User> inserted = userRepository.saveAll(users);
        return coreMapper.toUserDTOs(inserted);
    }

    @Override
    public UserDTO getOne(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return coreMapper.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        if(CollectionUtils.isEmpty(users)) {
            throw new UserNotFoundException();
        }
        return coreMapper.toUserDTOs(users);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteOne(int id) {
        userRepository.deleteById(id);
    }
}
