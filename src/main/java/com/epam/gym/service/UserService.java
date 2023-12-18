package com.epam.gym.service;

import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.LoginDTO;

public interface UserService {
    User create(String firstName, String lastName);

    void login(LoginDTO loginDTO);

    void changePassword(ChangePasswordDTO changePasswordDTO);


}
