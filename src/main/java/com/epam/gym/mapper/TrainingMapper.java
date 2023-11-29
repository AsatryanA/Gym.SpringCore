package com.epam.gym.mapper;

import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.request.TrainingRequestDTO;
import com.epam.gym.entity.dto.response.TrainingResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingMapper {
    private final TrainingTypeMapper trainingTypeMapper;

    public Training toTraining(TrainingRequestDTO trainingRequestDTO) {
        if (trainingRequestDTO == null) {
            throw new EntityNotFoundException("Training not found");
        }
        return Training.builder().date(trainingRequestDTO.getDate()).duration(trainingRequestDTO.getDuration()).name(trainingRequestDTO.getName()).build();
    }

    public TrainingResponseDTO toTrainingResponseDTO(Training training) {
        if (training == null) {
            throw new EntityNotFoundException("Training not found");
        }
        return TrainingResponseDTO.builder().id(training.getId()).date(training.getDate()).duration(training.getDuration()).name(training.getName()).trainingType(trainingTypeMapper.toTrainingTypeResponseDto(training.getTrainingType())).build();
    }
}
