package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Training;
import com.epam.gym.exception.EntityCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainingDAOImpl implements TrainingDAO {

    private final Session session;


    @Override
    public Training create(Training training) {
        log.info("Creating training: {} , {}",
                training.getTrainingType().getName(),
                training.getTrainer().getUser().getUsername());
        try {
            session.beginTransaction();
            session.persist(training);
            session.getTransaction().commit();
            return training;
        } catch (Exception e) {
            log.error("Error while creating training: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityCreationException(e.getMessage());
        }
    }

    @Override
    public List<Training> getByIds(List<Long> ids) {
        log.info("Getting trainings by ids: {}", ids);
        return session.createQuery("from Training where id in (:ids)", Training.class)
                .setParameterList("ids", ids)
                .list();
    }
}