package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.dto.TraineeDTO;
import com.epam.gym.mapper.TraineeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TraineeService {

    private final TraineeDAO traineeDAO;
    private final UserService userService;

    @Autowired
    public TraineeService(TraineeDAO traineeDAO, UserService userService) {
        this.traineeDAO = traineeDAO;
        this.userService = userService;
    }

    public Trainee createTrainee(TraineeDTO traineeDTO) {
        log.info("Creating trainee: {}", traineeDTO);
        var user = userService.createUser(traineeDTO);
        var trainee = TraineeMapper.toTrainee(traineeDTO, user.getId());
        return traineeDAO.createTrainee(trainee);
    }

    public Trainee getTraineeById(Long id) {
        log.info("Getting trainee by id: {}", id);
        return traineeDAO.getTraineeById(id);
    }

    public void deleteTrainee(Long id) {
        log.info("Deleting trainee with id: {}", id);
        traineeDAO.deleteTrainee(id);
    }

    public Trainee updateTrainee(TraineeDTO traineeDTO) {
        log.info("Updating trainee: {}", traineeDTO);
        var user = userService.createUser(traineeDTO);
        var trainee = TraineeMapper.toTrainee(traineeDTO, user.getId());
        return traineeDAO.updateTrainee(trainee);
    }

}
