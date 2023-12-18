package com.epam.gym.dao.impl;

import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.LoginDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.criteria.Expression;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    private Query<User> query;

    @Mock
    private javax.persistence.criteria.CriteriaBuilder criteriaBuilder;

    @Mock
    private javax.persistence.criteria.Root<User> userRoot;

    @Mock
    private javax.persistence.criteria.CriteriaQuery<User> criteriaQuery;

    @InjectMocks
    private UserDAOImpl userDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        query = mock(Query.class);
    }

    @Test
    void shouldGetUserById() {
        Long userId = 1L;
        User user = new User();
        when(session.get(User.class, userId)).thenReturn(user);
        Optional<User> result = userDAO.getById(userId);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenUserNotFound() {
        Long userId = 1L;
        when(session.get(User.class, userId)).thenReturn(null);
        Optional<User> result = userDAO.getById(userId);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldLogin() {
        LoginDTO loginDTO = new LoginDTO("username", "password");
        User user = new User();

        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(userRoot);
        when(criteriaQuery.select(userRoot)).thenReturn(criteriaQuery);
        when(criteriaQuery.where((Expression<Boolean>) any())).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(user);
        Optional<User> result = userDAO.login(loginDTO);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void shouldUpdateUser() {
        User user = new User();
        when(session.merge(user)).thenReturn(user);
        Optional<User> result = userDAO.update(user);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void shouldReturnFalseWhenUsernameNotAvailable() {
        String username = "existingUser";
        when(session.createQuery(any(), eq(User.class))).thenReturn(query);
        when(query.setParameter(eq("username"), eq(username))).thenReturn(query);
        when(query.uniqueResultOptional()).thenReturn(Optional.of(new User()));
        boolean result = userDAO.isUsernameAvailable(username);
        assertFalse(result);
    }
}
