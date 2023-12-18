package com.epam.gym.dao.impl;

import com.epam.gym.dao.UserDAO;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> getById(Long id) {
        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    public Optional<User> login(LoginDTO loginDTO) {
        var session = sessionFactory.getCurrentSession();
        var builder = session.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(User.class);
        var userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        criteriaQuery.where(
                builder.equal(userRoot.get("username"), loginDTO.getUsername()),
                builder.equal(userRoot.get("password"), loginDTO.getPassword()));
        return Optional.ofNullable(session.createQuery(criteriaQuery).getSingleResult());
    }

    @Override
    public Optional<User> update(User user) {
        var session = sessionFactory.getCurrentSession();
        session.merge(user);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where username = :username", User.class)
                .setParameter("username", username).uniqueResult() != null;
    }
}