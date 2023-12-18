package com.epam.gym.dao.impl;

import com.epam.gym.entity.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TrainingDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private TrainingDAOImpl trainingDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTraining() {
        Training training = new Training();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        trainingDAO.create(training);
        verify(session).persist(training);
    }
}
