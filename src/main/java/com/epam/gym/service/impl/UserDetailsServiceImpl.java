package com.epam.gym.service.impl;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.exception.VerificationException;
import com.epam.gym.model.User;
import com.epam.gym.model.dto.UserDetailsImpl;
import com.epam.gym.repository.UserRepository;
import com.epam.gym.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(username)) {
            throw new VerificationException("blocked");
        }
        var user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(User.class, username));
        return UserDetailsImpl.build(user.getId(), user.getUsername(), user.getPassword());
    }
}
