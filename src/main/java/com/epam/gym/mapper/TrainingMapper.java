package com.epam.gym.mapper;

import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.TrainingDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {
    public Training toTraining(TrainingDTO trainingDTO) {
        return new Training(null,
                trainingDTO.getTrainerId(),
                trainingDTO.getName(),
                trainingDTO.getTypeId(),
                trainingDTO.getDate(),
                trainingDTO.getDuration());
    }
}
