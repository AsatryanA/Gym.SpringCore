package com.epam.gym.dao;

import com.epam.gym.entity.Trainee;

import java.util.Optional;

public interface TraineeDAO {
    Optional<Trainee> create(Trainee trainee);

    Optional<Trainee> getById(Long id);

    Trainee update(Trainee trainee);

    void delete(Trainee trainee);
}
