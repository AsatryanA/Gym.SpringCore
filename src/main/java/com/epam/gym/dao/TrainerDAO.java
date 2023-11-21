package com.epam.gym.dao;

import com.epam.gym.entity.Trainer;

import java.util.List;

public interface TrainerDAO {
    Trainer create(Trainer trainee);

    Trainer update(Trainer trainee);

    void delete(Long id);

    Trainer getById(Long id);

    List<Trainer> getAll();
}

