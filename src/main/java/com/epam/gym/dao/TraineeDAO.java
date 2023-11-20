package com.epam.gym.dao;

import com.epam.gym.entity.Trainee;

import java.util.List;

public interface TraineeDAO {
    Trainee createTrainee(Trainee trainee);

    Trainee updateTrainee(Trainee trainee);

    void deleteTrainee(Long id);

    Trainee getTraineeById(Long id);

    List<Trainee> getAllTrainees();
}
