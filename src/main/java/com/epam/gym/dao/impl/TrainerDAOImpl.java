package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.entity.Trainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainerDAOImpl implements TrainerDAO {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<Trainer> create(Trainer trainer) {
        var session = sessionFactory.getCurrentSession();
        session.persist(trainer);
        if (trainer.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(trainer);
    }

    @Override
    public Optional<Trainer> getById(Long id) {
        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Trainer.class, id));
    }

    @Override
    public Trainer update(Trainer trainer) {
        var currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getByIds(List<Long> trainerIds) {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("from Trainer where id in (:ids)", Trainer.class)
                .setParameterList("ids", trainerIds)
                .list();
    }
}
