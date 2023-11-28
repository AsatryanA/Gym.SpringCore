package com.epam.gym.service;

import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Training;
import com.epam.gym.entity.dto.request.TrainingRequestDTO;
import com.epam.gym.mapper.TrainingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingDAO trainingDAO;
    private final TrainingMapper trainingMapper;


    public Training create(TrainingRequestDTO trainingRequestDTO) {
        log.info("Creating training: {}", trainingRequestDTO);
        return trainingDAO.create(trainingMapper.toTraining(trainingRequestDTO));
    }
}
