package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.dao.UserRepository;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO insertOne(CreateUserRequest createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        User inserted = userRepository.save(user);
        return modelMapper.map(inserted, UserDTO.class);
    }

    @Override
    public List<UserDTO> insertAll(List<CreateUserRequest> createUserRequests) {
        List<User> users = modelMapper.map(createUserRequests, new TypeToken<List<User>>() {}.getType());
        List<User> inserted = userRepository.saveAll(users);
        return modelMapper.map(inserted, new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Override
    public UserDTO getOne(int id) {
        User user = userRepository.findById(id).orElse(new User());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Override
    public void deleteOne(int id) {
        userRepository.deleteById(id);
    }
}
