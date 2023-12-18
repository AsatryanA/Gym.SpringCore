package com.epam.gym.dao;

import com.epam.gym.dao.impl.TraineeDAOImpl;
import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class TraineeDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private TraineeDAOImpl traineeDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTrainee() {
        Trainee trainee = Trainee.builder()
                .id(1L)
                .user(User.builder()
                        .id(1L)
                        .firstName("firstName").lastName("lastName").build()).build();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Optional<Trainee> result = traineeDAO.create(trainee);
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenCreateFails() {
        Trainee trainee = new Trainee();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Optional<Trainee> result = traineeDAO.create(trainee);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldGetTraineeById() {
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(Trainee.class, traineeId)).thenReturn(trainee);
        Optional<Trainee> result = traineeDAO.getById(traineeId);
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenTraineeNotFound() {
        Long traineeId = 1L;
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(Trainee.class, traineeId)).thenReturn(null);
        Optional<Trainee> result = traineeDAO.getById(traineeId);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldUpdateTrainee() {
        Trainee trainee = new Trainee();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Trainee result = traineeDAO.update(trainee);
        assertEquals(trainee, result);
    }

    @Test
    void shouldDeleteTrainee() {
        Trainee trainee = new Trainee();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        traineeDAO.delete(trainee);
    }
}
