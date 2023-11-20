package com.epam.gym.dao.impl;

import com.epam.gym.dao.UserDAO;
import com.epam.gym.entity.User;
import com.epam.gym.util.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserDAOImpl implements UserDAO {
    private static Long userIdCounter = 11L;

    @Autowired
    private InMemoryStorage storage;

    @Override
    public User createUser(User user) {
        log.info("Creating user: {}", user);
        var noneMatch = storage.getUserStorage().values().stream().noneMatch(u -> u.getUsername().equals(user.getUsername()));
        if (noneMatch) {
            user.setId(userIdCounter++);
            storage.getUserStorage().put(user.getId(), user);
            log.info("User with id {} was created", user.getId());
            return user;
        } else {
            log.error("User with id {} already exists", user.getId());
            throw new RuntimeException("User with id " + user.getId() + " already exists");
        }
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating user: {}", user);
        var user1 = storage.getUserStorage().get(user.getId());
        if (user1 != null) {
            storage.getUserStorage().put(user.getId(), user);
            log.info("User with id {} was updated", user.getId());
            return user;
        } else {
            log.error("User with id {} doesn't exist", user.getId());
            throw new RuntimeException("User with id " + user.getId() + " doesn't exist");
        }
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        storage.getUserStorage().remove(id);
        log.info("User with id {} was deleted", id);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Getting user by id: {}", id);
        return storage.getUserStorage().get(id);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return storage.getUserStorage().values().stream().toList();
    }

    public boolean isUsernameAvailable(String username) {
        return storage.getUserStorage().values()
                .stream()
                .noneMatch(u -> u.getUsername() != null &&
                        u.getUsername().equals(username));
    }
}
