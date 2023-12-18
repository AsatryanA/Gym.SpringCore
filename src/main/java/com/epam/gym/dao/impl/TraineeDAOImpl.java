package com.epam.gym.dao.impl;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.entity.Trainee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TraineeDAOImpl implements TraineeDAO {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<Trainee> create(Trainee trainee) {
        var session = sessionFactory.getCurrentSession();
        session.persist(trainee);
        if (trainee.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(trainee);
    }

    @Override
    public Optional<Trainee> getById(Long id) {
        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Trainee.class, id));
    }

    @Override
    public Trainee update(Trainee trainee) {
        var session = sessionFactory.getCurrentSession();
        session.merge(trainee);
        return trainee;
    }

    public void delete(Trainee trainee) {
        var session = sessionFactory.getCurrentSession();
        session.remove(trainee);
    }
}