package com.epam.gym.dao;

import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;

import java.util.List;

public interface UserDAO {

    User update(User user);

    void delete(Long id);

    User getById(Long id);

    List<User> getAll();

    boolean isUsernameAvailable(String username);

}
