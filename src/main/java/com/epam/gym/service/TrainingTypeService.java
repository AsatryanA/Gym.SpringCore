package com.epam.gym.service;

import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;

import java.util.List;

public interface TrainingTypeService {
    List<TrainingTypeResponseDTO> getAll();

    TrainingType getById(Long id);
}
