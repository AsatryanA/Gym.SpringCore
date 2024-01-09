package com.epam.gym.service;

public interface LoginAttemptService {
    void loginFailed(String username);

    boolean isBlocked(String username);
}
