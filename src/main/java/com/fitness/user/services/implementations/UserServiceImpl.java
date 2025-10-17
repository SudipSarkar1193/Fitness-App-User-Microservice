package com.fitness.user.services.implementations;

import com.fitness.user.dtos.RegisterUserRequestDTO;
import com.fitness.user.dtos.UserResponseDTO;
import com.fitness.user.models.User;
import com.fitness.user.respositories.UserRepository;
import com.fitness.user.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;

    @Override
    public UserResponseDTO registerUser(RegisterUserRequestDTO requestDTO)  {

        System.out.println("Registering user with email: " + requestDTO.getEmail());


        if(userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalStateException("Email already exists");
        }

        User user = new User();

        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        user.setUuid(UUID.randomUUID());

        String pass = "password123";

        user.setPassword(pass);


        User savedUser = userRepository.save(user);

        return mapUserToUserResponseDTO(savedUser);


    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new IllegalStateException("No users found");
        }

        List<UserResponseDTO> userDtoList = new ArrayList<>();
        for(User user : users) {
            System.out.println("User:"+ user);

            userDtoList.add(mapUserToUserResponseDTO(user));
        }

        return userDtoList;
    }

    @Override
    public UserResponseDTO getUserByUuid(UUID uuid) {
        if(uuid == null) {
            throw new IllegalStateException("UUID cannot be null");
        }
        User user = userRepository.findUserByUuid(uuid)
                .orElseThrow(() -> new IllegalStateException("User not found with UUID: " + uuid));
        return mapUserToUserResponseDTO(user);
    }

    private UserResponseDTO mapUserToUserResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUuid(user.getUuid());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole().name());
        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());
        return responseDTO;
    }
}
