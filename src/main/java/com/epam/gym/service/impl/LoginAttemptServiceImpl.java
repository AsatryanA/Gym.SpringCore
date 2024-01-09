package com.epam.gym.service.impl;

import com.epam.gym.service.LoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    public static final int MAX_ATTEMPT = 3;
    public static final int DURATION = 20;
    private final LoadingCache<String, Integer> attemptsCache;


    public LoginAttemptServiceImpl() {
        attemptsCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(DURATION, TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    public Integer load(String username) {
                        return 0;
                    }
                });
    }

    @Override
    public void loginFailed(String username) {
        int attempts;
        try {
            attempts = attemptsCache.get(username);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(username, attempts);
    }

    @Override
    public boolean isBlocked(String username) {
        try {
            return attemptsCache.get(username) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
