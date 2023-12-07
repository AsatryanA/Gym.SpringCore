package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainingDAOImpl implements TrainingDAO {

    private final SessionFactory sessionFactory;

    @Override
    public void create(Training training) {
        log.info("Creating training: {}",
                training.getTrainer().getUser().getUsername());
        var session = sessionFactory.getCurrentSession();
        session.persist(training);
    }
}