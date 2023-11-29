package com.epam.gym.dao.impl;

import com.epam.gym.dao.UserDAO;
import com.epam.gym.entity.User;
import com.epam.gym.exception.EntityUpdateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final Session session;

    @Override
    public User update(User user) {
        log.info("Updating user: {}", user);
        try {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            log.error("Error while updating user: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityUpdateException(e.getMessage());
        }
    }

    @Override
    public User getById(Long id) {
        log.info("Getting user with id: {}", id);
        return session.get(User.class, id);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        log.info("Checking if username is available: {}", username);
        return session.createQuery("from User where username = :username", User.class).setParameter("username", username).uniqueResult() != null;
    }


}