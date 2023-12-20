package com.epam.gym.service;

import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainingTypeService {
    List<TrainingTypeResponseDTO> getAll(Pageable pageable);

    TrainingType getById(Long id);
}
