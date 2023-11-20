package com.epam.gym.dao;

import com.epam.gym.entity.User;

import java.util.List;

public interface UserDAO {
    User createUser(User trainee);

    User updateUser(User trainee);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

    public boolean isUsernameAvailable(String username);
}
