package com.fitness.user.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RegisterUserRequestDTO {
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
