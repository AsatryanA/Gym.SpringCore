package com.epam.gym.dao;

import com.epam.gym.entity.Trainer;

import java.util.List;

public interface TrainerDAO {
    Trainer createTrainer(Trainer trainee);

    Trainer updateTrainer(Trainer trainee);

    void deleteTrainer(Long id);

    Trainer getTrainerById(Long id);

    List<Trainer> getAllTrainers();
}

