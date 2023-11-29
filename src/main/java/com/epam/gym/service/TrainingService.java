package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.dao.TrainingTypeDAO;
import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.request.TrainingRequestDTO;
import com.epam.gym.entity.dto.response.TrainingResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import com.epam.gym.mapper.TrainingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingDAO trainingDAO;
    private final TrainingMapper trainingMapper;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;
    private final TrainingTypeDAO trainingTypeDAO;


    public Training create(TrainingRequestDTO trainingRequestDTO) {
        log.info("Creating training: {}", trainingRequestDTO);
        var training = trainingMapper.toTraining(trainingRequestDTO);
        training.setTrainee(traineeDAO.getById(trainingRequestDTO.getTraineeId()));
        training.setTrainer(trainerDAO.getById(trainingRequestDTO.getTrainerId()));
        training.setTrainingType(trainingTypeDAO.getById(trainingRequestDTO.getTrainingTypeId()));
        return trainingDAO.create(training);
    }

    public List<TrainingResponseDTO> getTrainingsByUsernameAndDuration(String username, Integer durationFrom, Integer durationTo) {
        log.info("Getting trainings by username: {}, duration from: {}, duration to: {}", username, durationFrom, durationTo);
        var trainings = trainingDAO.getTrainingsByUsernameAndDuration(username, durationFrom, durationTo);
        if (trainings == null) {
            throw new EntityNotFoundException("Trainings not found");
        }
        return trainings.stream()
                .map(trainingMapper::toTrainingResponseDTO)
                .toList();
    }
}
