package com.epam.gym.mapper;

import com.epam.gym.model.User;
import com.epam.gym.model.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserResponseDTO userResponseDTO) {
        return User.builder()
                .firstName(userResponseDTO.getFirstName())
                .lastName(userResponseDTO.getLastName())
                .username(userResponseDTO.getUsername())
                .password(userResponseDTO.getEncodedPassword())
                .isActive(userResponseDTO.getIsActive())
                .build();
    }
}
