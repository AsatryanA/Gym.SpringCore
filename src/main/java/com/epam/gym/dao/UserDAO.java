package com.epam.gym.dao;

import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.LoginDTO;

import java.util.Optional;

public interface UserDAO {

    Optional<User> update(User user);

    Optional<User> getById(Long id);

    boolean isUsernameAvailable(String username);

    Optional<User> login(LoginDTO loginDTO);
}
