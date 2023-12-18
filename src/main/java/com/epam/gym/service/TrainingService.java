package com.epam.gym.service;

import com.epam.gym.entity.dto.request.TrainingRequestDTO;

public interface TrainingService {
    void create(TrainingRequestDTO trainingRequestDTO);
}
