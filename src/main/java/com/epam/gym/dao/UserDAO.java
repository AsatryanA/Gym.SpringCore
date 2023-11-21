package com.epam.gym.dao;

import com.epam.gym.entity.User;

import java.util.List;

public interface UserDAO {
    User create(User trainee);

    User update(User trainee);

    void delete(Long id);

    User getById(Long id);

    List<User> getAll();

    public boolean isUsernameAvailable(String username);
}
