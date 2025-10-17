package com.fitness.user.services.contracts;

import com.fitness.user.dtos.RegisterUserRequestDTO;
import com.fitness.user.dtos.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseDTO registerUser(RegisterUserRequestDTO requestDTO) ;

    List<UserResponseDTO> getAllUsers();
}
