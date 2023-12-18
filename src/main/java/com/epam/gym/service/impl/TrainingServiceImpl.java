package com.epam.gym.service.impl;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.dto.request.TrainingRequestDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingDAO trainingDAO;
    private final TrainingMapper trainingMapper;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;

    @Transactional
    public void create(TrainingRequestDTO trainingRequestDTO) {
        log.info("Creating training: {}", trainingRequestDTO);
        var training = trainingMapper.toTraining(trainingRequestDTO);
        var trainee = traineeDAO.getById(trainingRequestDTO.getTraineeId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainee.class, trainingRequestDTO.getTraineeId()));
        var trainer = trainerDAO.getById(trainingRequestDTO.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, trainingRequestDTO.getTrainerId()));
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        trainingDAO.create(training);
    }
}
