package com.fitness.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class RegisterUserRequestDTO {
    @NotBlank(message = "First name cannot be blank")
    @Size(min=3, message="First name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min=3, message="Last name must be at least 3 characters long")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
