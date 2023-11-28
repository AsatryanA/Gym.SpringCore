package com.epam.gym.mapper;

import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeMapper {
    public TrainingTypeResponseDTO toTrainingTypeResponseDto(TrainingType specialization) {
        if (specialization == null) {
            throw new EntityNotFoundException("specialization not found");
        }
        return TrainingTypeResponseDTO.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .build();
    }

    public TrainingType toTrainingType(TrainingTypeResponseDTO specialization) {
        if (specialization == null) {
            throw new EntityNotFoundException("specialization not found");
        }
        return TrainingType.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .build();
    }
}
