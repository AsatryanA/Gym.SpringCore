package com.epam.gym.dao;

import com.epam.gym.entity.TrainingType;

import java.util.List;

public interface TrainingTypeDAO {
    TrainingType getById(Long id);

    List<TrainingType> getAll();
}
