package com.epam.gym.service;

import com.epam.gym.model.dto.request.ChangeLoginDTO;
import com.epam.gym.model.dto.request.LoginDTO;
import com.epam.gym.model.dto.response.LoginResponseDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    LoginResponseDTO login(LoginDTO loginDTO);

    void changeLogin(ChangeLoginDTO changeLoginDTO);

    void logout(HttpServletRequest request);
}
