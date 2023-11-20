package com.epam.gym.service;

import com.epam.gym.dao.UserDAO;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.BaseUserDTO;
import com.epam.gym.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public <T extends BaseUserDTO> User createUser(T userDTO) {
        log.info("Creating user: {}", userDTO);
        var user = UserMapper.toUser(userDTO);
        int serialNumber = 1;
        if (!userDAO.isUsernameAvailable(user.getUsername())) {
            while (!userDAO.isUsernameAvailable(user.getUsername() + serialNumber)) {
                serialNumber++;
            }
            user.setUsername(user.getUsername() + serialNumber);
        }
        return userDAO.createUser(user);
    }

}
