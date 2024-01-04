package com.epam.gym.service;

import com.epam.gym.model.dto.request.TrainingRequestDTO;

public interface TrainingService {
    void create(TrainingRequestDTO trainingRequestDTO);
}
