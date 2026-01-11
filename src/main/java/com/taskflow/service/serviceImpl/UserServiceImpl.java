package com.taskflow.service.serviceImpl;

import com.taskflow.dto.UserLoginRequestDto;
import com.taskflow.dto.UserLoginResponseDto;
import com.taskflow.dto.UserRequestDto;
import com.taskflow.dto.UserResponseDto;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.UserService;
import com.taskflow.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
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

    @Override
    public List<UserResponseDto> getUser() {
        List<User> all = userRepository.findAll();
        return all.stream().map(user -> new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        )).collect(Collectors.toList());
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

//        String token = jwtUtil.generateToken(user.getEmail());
//        UserLoginResponseDto ulrd = new UserLoginResponseDto();
//        ulrd.setToken(token);
//        return ulrd;

        return new UserLoginResponseDto(jwtUtil.generateToken(user.getEmail()));
    }
}

