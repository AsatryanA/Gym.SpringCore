package com.epam.gym.service;

import com.epam.gym.model.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO create(String firstName, String lastName);
}
