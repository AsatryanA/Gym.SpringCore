package com.epam.gym.service.impl;

import com.epam.gym.service.LoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {
    @Value("${fieldLoginDuration}")

    public static final int MAX_ATTEMPT = 3;
    @Value("${field-loginDuration}")
    public static final int DURATION = 5;
    private final LoadingCache<String, Integer> attemptsCache;
    private final HttpServletRequest request;

    public LoginAttemptServiceImpl(HttpServletRequest request) {
        this.request = request;
        attemptsCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(DURATION, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    public Integer load(final String key) {
                        return 0;
                    }
                });
    }

    @Override
    public void loginFailed(String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    @Override
    public boolean isBlocked() {
        try {
            return attemptsCache.get(getClientIP()) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    private String getClientIP() {
        final var xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
