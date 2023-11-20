package com.epam.gym.dao.impl;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.entity.Trainee;
import com.epam.gym.util.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TraineeDAOImpl implements TraineeDAO {
    private static Long traineeIdCounter = 11L;

    @Autowired
    private InMemoryStorage storage;

    @Override
    public Trainee createTrainee(Trainee trainee) {

        log.info("Creating trainee: {}", trainee);
        trainee.setId(traineeIdCounter++);
        storage.getTraineeStorage().put(trainee.getId(), trainee);
        return trainee;
    }


    @Override
    public Trainee updateTrainee(Trainee trainee) {
        log.info("Updating trainee: {}", trainee);
        var trainee1 = storage.getTraineeStorage().get(trainee.getId());
        if (trainee1 != null) {
            storage.getTraineeStorage().put(trainee.getId(), trainee);
            return trainee;
        } else {
            log.error("Trainee with id {} doesn't exist", trainee.getId());
            throw new RuntimeException("Trainee with id " + trainee.getId() + " doesn't exist");
        }
    }

    @Override
    public void deleteTrainee(Long id) {
        log.info("Deleting trainee with id: {}", id);
        storage.getTraineeStorage().remove(id);
        log.info("Trainee with id {} was deleted", id);
    }

    @Override
    public Trainee getTraineeById(Long id) {
        log.info("Getting trainee by id: {}", id);
        return storage.getTraineeStorage().get(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        log.info("Getting all trainees");
        return storage.getTraineeStorage().values().stream().toList();
    }
}
