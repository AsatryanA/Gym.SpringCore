package com.epam.gym.service;

import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.TrainingDTO;
import com.epam.gym.mapper.TrainingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainingService {
    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingService(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    public Training createTraining(TrainingDTO training) {
        log.info("Creating training: {}", training);
        return trainingDAO.createTraining(TrainingMapper.toTraining(training));
    }

    public Training getTrainingById(Long id) {
        log.info("Getting training by id: {}", id);
        return trainingDAO.getTrainingById(id);
    }
}
