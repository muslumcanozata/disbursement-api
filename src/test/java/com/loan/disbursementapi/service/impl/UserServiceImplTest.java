package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.dao.UserRepository;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.mapper.CoreMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private CoreMapper coreMapper;

    @Test
    public void insertOne_ThenReturn() {
        //given
        CreateUserRequest createUserRequest = Instancio.create(CreateUserRequest.class);
        User user = Instancio.create(User.class);
        UserDTO expected = Instancio.create(UserDTO.class);

        //when
        when(coreMapper.toUserFromCreateUserRequest(Mockito.any(CreateUserRequest.class))).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(coreMapper.toUserDTO(user)).thenReturn(expected);

        UserDTO actual = service.insertOne(createUserRequest);

        //then
        verify(repository, times(1)).save(Mockito.any(User.class));
        then(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void insertAll_ThenReturn() {
        //given
        List<CreateUserRequest> createUserRequests = Instancio.ofList(CreateUserRequest.class).size(2).create();
        List<User> users = Instancio.ofList(User.class).size(2).create();
        List<UserDTO> expected = Instancio.ofList(UserDTO.class).size(2).create();

        //when
        when(coreMapper.toUserFromListOfCreateUserRequest(Mockito.anyList())).thenReturn(users);
        when(repository.saveAll(users)).thenReturn(users);
        when(coreMapper.toUserDTOs(users)).thenReturn(expected);

        List<UserDTO> actual = service.insertAll(createUserRequests);

        //then
        verify(repository, times(1)).saveAll(Mockito.anyList());
        then(actual.get(0).getId()).isEqualTo(expected.get(0).getId());
    }

    @Test
    public void getOne_ThenReturn() {
        //given
        User user = Instancio.create(User.class);
        UserDTO expected = Instancio.create(UserDTO.class);

        //when
        when(coreMapper.toUserDTO(Mockito.any(User.class))).thenReturn(expected);
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

        UserDTO actual = service.getOne(0);

        //then
        verify(repository, times(1)).findById(Mockito.anyInt());
        then(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void getAll_ThenReturn() {
        //given
        List<User> users = Instancio.ofList(User.class).size(2).create();
        List<UserDTO> expected = Instancio.ofList(UserDTO.class).size(2).create();

        //when
        when(repository.findAll()).thenReturn(users);
        when(coreMapper.toUserDTOs(users)).thenReturn(expected);

        List<UserDTO> actual = service.getAll();

        //then
        verify(repository, times(1)).findAll();
        then(actual.get(0).getId()).isEqualTo(expected.get(0).getId());
    }

    @Test
    public void getUser_ThenReturn() {
        //given
        User expected = Instancio.create(User.class);

        //when
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(expected));

        User actual = service.getUser(0);

        //then
        verify(repository, times(1)).findById(Mockito.anyInt());
        then(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void deleteOne_ThenReturnNothing() {
        //when
        doNothing().when(repository).deleteById(Mockito.anyInt());

        service.deleteOne(0);

        //then
        verify(repository, times(1)).deleteById(Mockito.anyInt());
    }
}
