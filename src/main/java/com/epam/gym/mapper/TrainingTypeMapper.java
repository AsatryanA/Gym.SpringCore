package com.epam.gym.mapper;

import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeMapper {
    public TrainingTypeResponseDTO toTrainingTypeResponseDto(TrainingType specialization) {
        return TrainingTypeResponseDTO.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .build();
    }
}
