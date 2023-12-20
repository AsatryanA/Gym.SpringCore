package com.epam.gym.dao.impl;

import com.epam.gym.entity.TrainingType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrainingTypeDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private TrainingTypeDAOImpl trainingTypeDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllTrainingTypes() {
        List<TrainingType> trainingTypes = Arrays.asList(new TrainingType(), new TrainingType(), new TrainingType());
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Query<TrainingType> query = mock(Query.class);
        when(session.createQuery("from TrainingType", TrainingType.class)).thenReturn(query);
        when(session.createQuery("from TrainingType", TrainingType.class).list()).thenReturn(trainingTypes);
        List<TrainingType> result = trainingTypeDAO.getAll(Pageable.unpaged());
        assertEquals(trainingTypes, result);
    }
}
