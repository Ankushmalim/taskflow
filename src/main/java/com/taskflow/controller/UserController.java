package com.taskflow.controller;

import com.taskflow.dto.UserLoginRequestDto;
import com.taskflow.dto.UserLoginResponseDto;
import com.taskflow.dto.UserRequestDto;
import com.taskflow.dto.UserResponseDto;
import com.taskflow.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(
            @Valid
            @RequestBody UserRequestDto userRequestDto
    ){
        UserResponseDto savedUser = userService.createUser(userRequestDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        List<UserResponseDto> allUsers = userService.getUser();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){
        return null;
    }


}
