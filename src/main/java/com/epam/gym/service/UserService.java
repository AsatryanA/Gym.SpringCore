package com.epam.gym.service;

import com.epam.gym.dao.UserDAO;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.UserRequestDTO;
import com.epam.gym.entity.dto.request.UserUpdateDTO;
import com.epam.gym.entity.dto.response.UserResponseDTO;
import com.epam.gym.mapper.UserMapper;
import com.epam.gym.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final UserMapper userMapper;

    public User create(UserRequestDTO userRequestDTO) {
        return User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .username(generateUsername(userRequestDTO.getFirstName(), userRequestDTO.getLastName()))
                .password(generatePassword())
                .isActive(true)
                .build();
    }

    public UserResponseDTO update(UserUpdateDTO userUpdateDTO) {
        var user = userMapper.toUser(userUpdateDTO);
        user.setId(userUpdateDTO.getId());
        user.setUsername(generateUsername(user.getFirstName(), user.getLastName()));
        return userMapper.toUserResponseDto(userDAO.update(user));
    }

    private String generateUsername(String firstName, String lastName) {
        var username = String.format("%s.%s", firstName, lastName);
        int serialNumber = 1;
        if (userDAO.isUsernameAvailable(username)) {
            while (userDAO.isUsernameAvailable(username + serialNumber)) {
                serialNumber++;
            }
            username = username + serialNumber;
        }
        return username;
    }

    private String generatePassword() {
        return PasswordGenerator.generateRandomPassword();
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO, User user) {
        log.info("Changing password for user with id: {}", changePasswordDTO.getId());
        if (user != null) {
            if (user.getPassword().equals(changePasswordDTO.getOldPassword())) {
                user.setPassword(changePasswordDTO.getNewPassword());
                userDAO.update(user);
            }
        }
    }
}
