package com.epam.gym.mapper;

import com.epam.gym.model.Training;
import com.epam.gym.model.dto.request.TrainingRequestDTO;
import com.epam.gym.model.dto.response.TraineeTrainingDTO;
import com.epam.gym.model.dto.response.TrainerTrainingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    public Training toTraining(TrainingRequestDTO trainingRequestDTO) {
        return Training.builder()
                .date(trainingRequestDTO.getDate())
                .duration(trainingRequestDTO.getDuration())
                .name(trainingRequestDTO.getName())
                .build();
    }

    public TraineeTrainingDTO toTraineeTrainingDTO(Training training, String traineeName) {
        return TraineeTrainingDTO.builder()
                .traineeName(traineeName)
                .name(training.getName())
                .date(training.getDate())
                .duration(training.getDuration())
                .trainingType(training.getTrainingType())
                .build();
    }

    public TrainerTrainingDTO toTrainerTrainingDTO(Training training, String trainerName) {
        return TrainerTrainingDTO.builder()
                .trainerName(trainerName)
                .name(training.getName())
                .date(training.getDate())
                .duration(training.getDuration())
                .trainingType(training.getTrainingType())
                .build();
    }
}