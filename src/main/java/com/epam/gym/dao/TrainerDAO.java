package com.epam.gym.dao;

import com.epam.gym.entity.Trainer;

import java.util.List;

public interface TrainerDAO {
    Trainer create(Trainer trainer);

    Trainer update(Trainer trainer);

    Trainer getById(Long id);

    Trainer getByUsername(String username);

    List<Trainer> getByIds(List<Long> trainerIds);

    Trainer findByUsernameAndPassword(String username, String password);

}

