package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TrainingDAOImpl implements TrainingDAO {
    private static Long trainingIdCounter = 11L;

    @Autowired
    private InMemoryStorage storage;

    @Override
    public Training create(Training training) {
        log.info("Creating training: {}", training);
        training.setId(trainingIdCounter++);
        storage.getTrainingStorage().put(training.getId(), training);
        log.info("Training with id {} was created", training.getId());
        return training;
    }

    @Override
    public Training update(Training training) {
        log.info("Updating training: {}", training);
        var training1 = storage.getTrainingStorage().get(training.getId());
        if (training1 != null) {
            storage.getTrainingStorage().put(training.getId(), training);
            log.info("Training with id {} was updated", training.getId());
            return training;
        } else {
            log.error("Training with id {} doesn't exist", training.getId());
            throw new RuntimeException("Training with id " + training.getId() + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting training with id: {}", id);
        storage.getTrainingStorage().remove(id);
        log.info("Training with id {} was deleted", id);
    }

    @Override
    public Training getById(Long id) {
        log.info("Getting training by id: {}", id);
        return storage.getTrainingStorage().get(id);
    }

    @Override
    public List<Training> getAll() {
        log.info("Getting all trainings");
        return storage.getTrainingStorage().values().stream().toList();
    }
}
