package com.taskflow.service;

import com.taskflow.dto.UserRequestDto;
import com.taskflow.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto userRequestDto);
    public List<UserResponseDto> getUser();
}
