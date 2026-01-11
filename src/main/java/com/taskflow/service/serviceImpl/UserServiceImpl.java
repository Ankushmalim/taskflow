package com.taskflow.service.serviceImpl;

import com.taskflow.dto.UserRequestDto;
import com.taskflow.dto.UserResponseDto;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword((userRequestDto.getPassword()));
        user.setRole(userRequestDto.getRole());

        User saved = userRepository.save(user);

        return new UserResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole(),
                saved.getCreatedAt()
        );
    }
}

