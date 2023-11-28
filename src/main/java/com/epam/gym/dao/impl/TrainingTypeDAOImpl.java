package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainingTypeDAO;
import com.epam.gym.entity.TrainingType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainingTypeDAOImpl implements TrainingTypeDAO {

    private final Session session;

    @Override
    public TrainingType getById(Long id) {
        log.info("Getting training type with id: {}", id);
        return session.get(TrainingType.class, id);
    }

    @Override
    public List<TrainingType> getAll() {
        log.info("Getting all training types");
        try {
            return session.createQuery("from TrainingType", TrainingType.class)
                    .list();
        } catch (Exception e) {
            log.error("Error while getting all training types: {}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
