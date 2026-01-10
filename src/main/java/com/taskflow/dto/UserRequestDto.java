package com.taskflow.dto;

import com.taskflow.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 20, message = "Name length should be maximum 20 character")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "please enter valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 12, message = "Password should be 6 to 12 character")
    private String password;

    @NotBlank(message = "Role is required")
    private Role role;
}
