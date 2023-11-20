package com.epam.gym.mapper;

import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.TrainingDTO;

public class TrainingMapper {
    public static Training toTraining(TrainingDTO trainingDTO) {
        return new Training(null,
                trainingDTO.getTrainerId(),
                trainingDTO.getName(),
                trainingDTO.getTypeId(),
                trainingDTO.getDate(),
                trainingDTO.getDuration());
    }
}
