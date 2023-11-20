package com.epam.gym.dao;

import com.epam.gym.entity.Training;

import java.util.List;

public interface TrainingDAO {
    Training createTraining(Training trainee);

    Training updateTraining(Training trainee);

    void deleteTraining(Long id);

    Training getTrainingById(Long id);

    List<Training> getAllTrainings();
}
