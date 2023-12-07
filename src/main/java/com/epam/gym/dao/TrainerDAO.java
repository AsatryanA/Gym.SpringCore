package com.epam.gym.dao;

import com.epam.gym.entity.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerDAO {

    Optional<Trainer> create(Trainer trainer);

    Optional<Trainer> getById(Long id);

    Trainer update(Trainer trainer);

    List<Trainer> getByIds(List<Long> trainerIds);
}

