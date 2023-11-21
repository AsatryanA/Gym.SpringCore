package com.epam.gym.mapper;

import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.BaseUserDTO;
import com.epam.gym.util.PasswordGenerator;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public <T extends BaseUserDTO> User toUser(T userDTO) {
        String username = String.format("%s.%s", userDTO.getFirstName(), userDTO.getLastName());
        String password = PasswordGenerator.generateRandomPassword();
        return new User(userDTO.getFirstName(), userDTO.getLastName(), username, password, true);
    }
}
