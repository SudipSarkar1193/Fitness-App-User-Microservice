package com.fitness.user.controllers;

import com.fitness.user.dtos.RegisterUserRequestDTO;
import com.fitness.user.dtos.ResponseDTO;
import com.fitness.user.dtos.UserResponseDTO;
import com.fitness.user.models.User;
import com.fitness.user.services.contracts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "User Service is up and running");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid RegisterUserRequestDTO requestDTO)  {
        if (requestDTO == null) {
            throw new IllegalStateException("Request body cannot be null");
        }
        UserResponseDTO userResponseDTO = null;
        try {
            userResponseDTO = userService.registerUser(requestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ResponseDTO responseDTO = new ResponseDTO(userResponseDTO,"User registered successfully");
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping("/{uuid}")
    ResponseEntity<ResponseDTO> getUserProfile(@PathVariable UUID uuid) {
        if (uuid == null) {
            throw new IllegalStateException("UUID cannot be null");
        }

        UserResponseDTO userResponseDTO = userService.getUserByUuid(uuid);

        ResponseDTO responseDTO = new ResponseDTO(userResponseDTO,
                "User profile fetched successfully");

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{uuid}/validate")
    ResponseEntity<ResponseDTO> validateUserProfile(@PathVariable UUID uuid) {
        if (uuid == null) {
            throw new IllegalStateException("UUID cannot be null");
        }
        boolean isValid = userService.isValidUser(uuid);

        return ResponseEntity.ok(
                new ResponseDTO(new HashMap<>().put("isValid",isValid),
                "User validated successfully")
        );
    }

    @GetMapping("/all")
    ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
