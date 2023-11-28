package com.epam.gym.dao;

import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;

import java.util.List;

public interface TrainerDAO {
    Trainer create(Trainer trainer);

    Trainer update(Trainer trainer);

    void delete(Long id);

    Trainer getById(Long id);

    List<Trainer> getAll();

    Trainer getByUsername(String username);

    List<Trainer> getByIds(List<Long> trainerIds);

}

