package com.epam.gym.dao;

import com.epam.gym.entity.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeDAO {

    List<TrainingType> getAll();

    Optional<TrainingType> getById(Long id);

}
