package com.epam.gym.util;

import java.security.SecureRandom;

public class PasswordGenerator {
    private PasswordGenerator() {
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    public static String generateRandomPassword() {
        var password = new StringBuilder();
        var random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        return password.toString();
    }
}
