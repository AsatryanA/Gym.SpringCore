package com.epam.gym.dao;

import com.epam.gym.entity.Trainee;

import java.util.List;

public interface TraineeDAO {
    Trainee create(Trainee trainee);

    Trainee update(Trainee trainee);

    void delete(Long id);

    Trainee getById(Long id);

    List<Trainee> getAll();
}
