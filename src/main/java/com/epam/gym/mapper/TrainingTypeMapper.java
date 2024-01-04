package com.epam.gym.mapper;

import com.epam.gym.model.TrainingType;
import com.epam.gym.model.dto.response.TrainingTypeResponseDTO;
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
