package com.epam.gym.dao;

import com.epam.gym.entity.Training;

import java.util.List;

public interface TrainingDAO {
    Training create(Training trainee);

    Training update(Training trainee);

    void delete(Long id);

    Training getById(Long id);

    List<Training> getAll();
}
