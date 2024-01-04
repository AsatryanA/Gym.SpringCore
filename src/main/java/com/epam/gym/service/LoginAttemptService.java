package com.epam.gym.service;

public interface LoginAttemptService {
    void loginFailed(String key);

    boolean isBlocked();
}
