package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.entity.Trainer;
import com.epam.gym.util.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TrainerDAOImpl implements TrainerDAO {

    private static Long trainerIdCounter = 11L;

    @Autowired
    private InMemoryStorage storage;


    @Override
    public Trainer createTrainer(Trainer trainer) {
        log.info("Creating trainer: {}", trainer);
        trainer.setId(trainerIdCounter++);
        storage.getTrainerStorage().put(trainer.getId(), trainer);
        log.info("Trainer with id {} was created", trainer.getId());
        return trainer;
    }


    @Override
    public Trainer updateTrainer(Trainer trainer) {
        log.info("Updating trainer: {}", trainer);
        var trainer1 = storage.getTrainerStorage().get(trainer.getId());
        if (trainer1 != null) {
            storage.getTrainerStorage().put(trainer.getId(), trainer);
            log.info("Trainer with id {} was updated", trainer.getId());
            return trainer;
        } else {
            log.error("Trainer with id {} doesn't exist", trainer.getId());
            throw new RuntimeException("Trainer with id " + trainer.getId() + " doesn't exist");
        }
    }

    @Override
    public void deleteTrainer(Long id) {
        log.info("Deleting trainer with id: {}", id);
        storage.getTrainerStorage().remove(id);
        log.info("Trainer with id {} was deleted", id);
    }

    @Override
    public Trainer getTrainerById(Long id) {
        log.info("Getting trainer by id: {}", id);
        return storage.getTrainerStorage().get(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        log.info("Getting all trainers");
        return storage.getTrainerStorage().values().stream().toList();
    }
}
