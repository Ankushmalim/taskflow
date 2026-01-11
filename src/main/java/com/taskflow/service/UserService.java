package com.taskflow.service;

import com.taskflow.dto.UserRequestDto;
import com.taskflow.dto.UserResponseDto;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto userRequestDto);
}
