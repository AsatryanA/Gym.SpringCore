package com.epam.gym.service;

import com.epam.gym.dao.UserDAO;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.LoginDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private static final boolean DEFAULT_IS_ACTIVE = true;

    @Transactional
    public User create(String firstName, String lastName) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(generateUsername(firstName, lastName))
                .password(generatePassword())
                .isActive(DEFAULT_IS_ACTIVE)
                .build();
    }

    @Transactional(readOnly = true)
    public void login(LoginDTO loginDTO) {
        log.info("Logging in user with username: {}", loginDTO.getUsername());
        userDAO.login(loginDTO).orElseThrow(() -> new ResourceNotFoundException(User.class, loginDTO.getUsername()));
    }

    @Transactional
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        log.info("Changing password for user with id: {}", changePasswordDTO.getId());
        var user = userDAO.getById(changePasswordDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(User.class, changePasswordDTO.getId()));
        if (user.getPassword().equals(changePasswordDTO.getOldPassword())) {
            user.setPassword(changePasswordDTO.getNewPassword());
            userDAO.update(user).orElseThrow(() -> new ResourceNotFoundException(User.class, user.getId()));
        }
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
}

