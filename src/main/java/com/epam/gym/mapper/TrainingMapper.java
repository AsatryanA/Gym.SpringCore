package com.epam.gym.mapper;

import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.request.TrainingRequestDTO;
import com.epam.gym.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {
    public Training toTraining(TrainingRequestDTO trainingRequestDTO) {
        if (trainingRequestDTO == null) {
            throw new EntityNotFoundException("Training not found");
        }
        return Training.builder()
                .date(trainingRequestDTO.getDate())
                .duration(trainingRequestDTO.getDuration())
                .name(trainingRequestDTO.getName())
                .build();
    }
}
