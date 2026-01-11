package com.taskflow.dto;

import com.taskflow.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;

    public UserResponseDto(Long id, String name, String email, Role role, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role.name();
        this.createdAt = createdAt;
    }
}
