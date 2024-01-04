package com.epam.gym.service.impl;

import com.epam.gym.model.dto.response.UserResponseDTO;
import com.epam.gym.repository.UserRepository;
import com.epam.gym.service.UserService;
import com.epam.gym.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final boolean DEFAULT_IS_ACTIVE = true;

    @Transactional
    public UserResponseDTO create(String firstName, String lastName) {
        final String rawPassword = generatePassword();
        return UserResponseDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(generateUsername(firstName, lastName))
                .encodedPassword(passwordEncoder.encode(rawPassword))
                .rawPassword(rawPassword)
                .isActive(DEFAULT_IS_ACTIVE)
                .build();
    }


    private String generateUsername(String firstName, String lastName) {
        var username = String.format("%s.%s", firstName, lastName);
        int serialNumber = 1;
        if (userRepository.existsByUsername(username)) {
            while (userRepository.existsByUsername(username + serialNumber)) {
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
