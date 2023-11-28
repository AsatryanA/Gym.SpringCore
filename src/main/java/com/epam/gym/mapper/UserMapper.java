package com.epam.gym.mapper;

import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.UserUpdateDTO;
import com.epam.gym.entity.dto.response.UserResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toUserResponseDto(User user) {
        if (user == null)
            throw new EntityNotFoundException("User not found");
        return UserResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

    public User toUser(UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO == null)
            throw new EntityNotFoundException("User not found");
        return User.builder()
                .password(userUpdateDTO.getPassword())
                .firstName(userUpdateDTO.getFirstName())
                .lastName(userUpdateDTO.getLastName())
                .build();
    }

    public User toUser(UserResponseDTO userResponseDTO) {
        if (userResponseDTO == null)
            throw new EntityNotFoundException("User not found");
        return User.builder()
                .firstName(userResponseDTO.getFirstName())
                .lastName(userResponseDTO.getLastName())
                .username(userResponseDTO.getUsername())
                .isActive(userResponseDTO.getIsActive())
                .build();
    }


}
