package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.entity.Trainer;
import com.epam.gym.exception.EntityCreationException;
import com.epam.gym.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainerDAOImpl implements TrainerDAO {
    private final Session session;

    @Override
    public Trainer create(Trainer trainer) {
        log.info("Creating trainer: {} , {}",
                trainer.getUser().getFirstName(),
                trainer.getUser().getLastName());
        try {
            session.beginTransaction();
            session.persist(trainer);
            session.getTransaction().commit();
            return trainer;
        } catch (Exception e) {
            log.error("Error while creating trainer: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityCreationException(e.getMessage());
        }
    }

    @Override
    public List<Trainer> getByIds(List<Long> trainerIds) {
        log.info("Getting trainers by ids: {}", trainerIds);
        return session.createQuery("from Trainer where id in (:ids)", Trainer.class)
                .setParameterList("ids", trainerIds)
                .list();
    }

    @Override
    public Trainer update(Trainer trainer) {
        log.info("Updating trainer: {}", trainer.getUser().getUsername());
        try {
            session.beginTransaction();
            session.merge(trainer);
            session.getTransaction().commit();
            return trainer;
        } catch (Exception e) {
            log.error("Error while updating trainer: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityCreationException(e.getMessage());
        }
    }

    @Override
    public Trainer getById(Long id) {
        log.info("Getting trainer with id: {}", id);
        return session.get(Trainer.class, id);
    }

    @Override
    public Trainer getByUsername(String username) {
        log.info("Getting trainer by username: {}", username);
        try {
            return session.createQuery("from Trainer where user.username = :username", Trainer.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (EntityNotFoundException e) {
            log.error("Error while getting trainer by username: {}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public Trainer findByUsernameAndPassword(String username, String password) {
        log.info("Getting trainer by username: {}, password: {}", username, password);
        var builder = session.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Trainer.class);
        var trainerRoot = criteriaQuery.from(Trainer.class);
        criteriaQuery.select(trainerRoot);
        criteriaQuery.where(
                builder.equal(trainerRoot.join("user").get("username"), username),
                builder.equal(trainerRoot.join("user").get("password"), password)
        );
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
