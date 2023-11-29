package com.epam.gym.dao;

import com.epam.gym.entity.User;

public interface UserDAO {
    User update(User user);

    User getById(Long id);

    boolean isUsernameAvailable(String username);

}
