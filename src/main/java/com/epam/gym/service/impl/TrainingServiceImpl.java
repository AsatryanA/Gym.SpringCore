package com.epam.gym.service.impl;

import com.epam.gym.model.Trainee;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.dto.request.TrainingRequestDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.repository.TraineeRepository;
import com.epam.gym.repository.TrainerRepository;
import com.epam.gym.repository.TrainingRepository;
import com.epam.gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    @Transactional
    public void create(TrainingRequestDTO trainingRequestDTO) {
        var training = trainingMapper.toTraining(trainingRequestDTO);
        var trainee = traineeRepository.findById(trainingRequestDTO.getTraineeId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainee.class, trainingRequestDTO.getTraineeId()));
        var trainer = trainerRepository.findById(trainingRequestDTO.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, trainingRequestDTO.getTrainerId()));
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        trainingRepository.save(training);
    }
}
