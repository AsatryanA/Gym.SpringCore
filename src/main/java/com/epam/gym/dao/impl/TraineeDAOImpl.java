package com.epam.gym.dao.impl;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.entity.Trainee;
import com.epam.gym.exception.EntityCreationException;
import com.epam.gym.exception.EntityDeleteException;
import com.epam.gym.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TraineeDAOImpl implements TraineeDAO {
    private final Session session;


    @Override
    public Trainee create(Trainee trainee) {
        log.info("Creating trainee: {} , {}", trainee.getUser().getFirstName(), trainee.getUser().getLastName());
        try {
            session.beginTransaction();
            session.persist(trainee);
            session.getTransaction().commit();
            return trainee;
        } catch (Exception e) {
            log.error("Error while creating trainee: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityCreationException(e.getMessage());
        }
    }

    @Override
    public Trainee getByUsername(String username) {
        log.info("Getting trainee by username: {}", username);
        try {
            return session.createQuery("from Trainee where user.username = :username", Trainee.class).setParameter("username", username).uniqueResult();
        } catch (EntityNotFoundException e) {
            log.error("Error while getting trainee by username: {}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Trainee> getByIds(List<Long> ids) {
        log.info("Getting trainees by ids: {}", ids);
        return session.createQuery("from Trainee where id in (:ids)", Trainee.class).setParameterList("ids", ids).list();
    }

    @Override
    public void deleteByUsername(String username) {
        log.info("Deleting trainee by username: {}", username);
        try {
            session.beginTransaction();
            session.createMutationQuery("delete from Trainee where user.username = :username").setParameter("username", username).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while deleting trainee: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityDeleteException(e.getMessage());
        }
    }

    @Override
    public Trainee update(Trainee trainee) {
        log.info("Updating trainee: {} , {}", trainee.getUser().getFirstName(), trainee.getUser().getLastName());
        try {
            session.beginTransaction();
            session.merge(trainee);
            session.getTransaction().commit();
            return trainee;
        } catch (Exception e) {
            log.error("Error while updating trainee: {}", e.getMessage());
            session.getTransaction().rollback();
            throw new EntityCreationException(e.getMessage());
        }
    }

    @Override
    public Trainee getById(Long id) {
        log.info("Getting trainee by id: {}", id);
        return session.get(Trainee.class, id);
    }

    @Override
    public List<Trainee> getAll() {
        log.info("Getting all trainees");
        return session.createQuery("from Trainee", Trainee.class).list();
    }
}