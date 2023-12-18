package com.epam.gym.dao.impl;

import com.epam.gym.entity.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrainerDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private TrainerDAOImpl trainerDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTrainer() {
        var trainer = Trainer.builder().id(1L).build();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        var result = trainerDAO.create(trainer);
        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenCreateFails() {
        var trainer = new Trainer();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        var result = trainerDAO.create(trainer);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldGetTrainerById() {
        var trainerId = 1L;
        var trainer = new Trainer();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(Trainer.class, trainerId)).thenReturn(trainer);
        var result = trainerDAO.getById(trainerId);
        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenTrainerNotFound() {
        var trainerId = 1L;
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(Trainer.class, trainerId)).thenReturn(null);
        var result = trainerDAO.getById(trainerId);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldUpdateTrainer() {
        var trainer = new Trainer();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        var result = trainerDAO.update(trainer);
        assertEquals(trainer, result);
    }

    @Test
    void shouldGetTrainersByIds() {
        List<Long> trainerIds = Arrays.asList(1L, 2L, 3L);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Query<Trainer> query = mock(Query.class);
        when(session.createQuery("from Trainer where id in (:ids)", Trainer.class)).thenReturn(query);
        when(query.setParameterList("ids", trainerIds)).thenReturn(query);
        List<Trainer> trainers = Arrays.asList(new Trainer(), new Trainer(), new Trainer());
        when(query.list()).thenReturn(trainers);
        List<Trainer> result = trainerDAO.getByIds(trainerIds);
        assertEquals(trainers, result);
    }
}
