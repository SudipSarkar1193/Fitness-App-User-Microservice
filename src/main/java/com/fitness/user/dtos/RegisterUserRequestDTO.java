package com.fitness.user.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RegisterUserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;

}
