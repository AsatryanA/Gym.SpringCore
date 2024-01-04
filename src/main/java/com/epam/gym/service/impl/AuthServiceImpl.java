package com.epam.gym.service.impl;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.model.User;
import com.epam.gym.model.dto.UserDetailsImpl;
import com.epam.gym.model.dto.request.ChangeLoginDTO;
import com.epam.gym.model.dto.request.LoginDTO;
import com.epam.gym.model.dto.response.LoginResponseDTO;
import com.epam.gym.repository.UserRepository;
import com.epam.gym.service.AuthService;
import com.epam.gym.util.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public LoginResponseDTO login(LoginDTO loginDTO) {
        var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));
        var userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        var token = jwtProvider.generateAccessToken(userDetails.getId(), userDetails.getUsername());
        return LoginResponseDTO.builder()
                .token(token)
                .build();
    }

    @Transactional
    public void changeLogin(ChangeLoginDTO changeLoginDTO) {
        var user = userRepository.findById(changeLoginDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(User.class, changeLoginDTO.getId()));
        if (passwordEncoder.matches(changeLoginDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changeLoginDTO.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        var token = jwtProvider.getToken(request);
        jwtProvider.invalidateToken(token);
    }
}
