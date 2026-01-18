package com.taskflow.service;

import com.taskflow.dto.UserLoginRequestDto;
import com.taskflow.dto.UserLoginResponseDto;
import com.taskflow.dto.UserRequestDto;
import com.taskflow.dto.UserResponseDto;
import com.taskflow.entity.Role;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.serviceImpl.UserServiceImpl;
import com.taskflow.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void createUser_success() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setName("Ankush");
        request.setEmail("ankush@gmail.com");
        request.setPassword("password");
        request.setRole(Role.valueOf("USER"));

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Ankush");
        savedUser.setEmail("ankush@gmail.com");
        savedUser.setPassword("encodedPass");
        savedUser.setRole(Role.valueOf("USER"));

        when(passwordEncoder.encode("password")).thenReturn("encodedPass");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserResponseDto response = userService.createUser(request);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Ankush", response.getName());
        assertEquals("ankush@gmail.com", response.getEmail());
        assertEquals("USER", response.getRole());
    }

    @Test
    void getUser_success() {
        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setEmail("test@gmail.com");
        user.setRole(Role.valueOf("USER"));

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDto> users = userService.getUser();

        assertEquals(1, users.size());
        assertEquals("Test", users.get(0).getName());
    }

    @Test
    void login_success() {
        UserLoginRequestDto request = new UserLoginRequestDto();
        request.setEmail("test@gmail.com");
        request.setPassword("password");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("encodedPass");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPass"))
                .thenReturn(true);
        when(jwtUtil.generateToken("test@gmail.com"))
                .thenReturn("jwt-token");

        UserLoginResponseDto response = userService.login(request);

        assertEquals("jwt-token", response.getToken());
    }

    @Test
    void login_invalidCredentials() {
        UserLoginRequestDto request = new UserLoginRequestDto();
        request.setEmail("test@gmail.com");
        request.setPassword("wrong");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("encodedPass");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "encodedPass"))
                .thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> userService.login(request));
    }

}
