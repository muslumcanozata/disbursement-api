package com.loan.disbursementapi.controller;

import com.loan.disbursementapi.controller.request.BatchCreateUserRequest;
import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.domain.dto.UserDTO;
import com.loan.disbursementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> insertOne(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.insertOne(createUserRequest), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<List<UserDTO>> insertAll(@RequestBody BatchCreateUserRequest batchCreateUserRequest) {
        return new ResponseEntity<>(userService.insertAll(batchCreateUserRequest.getCreateUserRequests()), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") int id) {
        userService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
