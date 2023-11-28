package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.TraineeRequestDTO;
import com.epam.gym.entity.dto.request.TraineeUpdateDTO;
import com.epam.gym.entity.dto.request.UserUpdateDTO;
import com.epam.gym.entity.dto.response.TraineeResponseDTO;
import com.epam.gym.mapper.TraineeMapper;
import com.epam.gym.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class TraineeService {

    private final TraineeDAO traineeDAO;
    private final TrainingDAO trainingDAO;
    private final TrainerDAO trainerDAO;
    private final UserService userService;
    private final TraineeMapper traineeMapper;
    private final UserMapper userMapper;

    public TraineeService(TraineeDAO traineeDAO, TrainingDAO trainingDAO, TrainerDAO trainerDAO, UserService userService, TraineeMapper traineeMapper, UserMapper userMapper) {
        this.traineeDAO = traineeDAO;
        this.trainingDAO = trainingDAO;
        this.trainerDAO = trainerDAO;
        this.userService = userService;
        this.traineeMapper = traineeMapper;
        this.userMapper = userMapper;
    }

    public TraineeResponseDTO create(TraineeRequestDTO traineeRequestDTO) {
        log.info("Creating trainee: {} , {}",
                traineeRequestDTO.getUser().getFirstName(),
                traineeRequestDTO.getUser().getLastName());
        var user = userService.create(traineeRequestDTO.getUser());
        var trainee = traineeMapper.toTrainee(traineeRequestDTO, user);
        trainee.setTrainings(trainingDAO.getByIds((traineeRequestDTO.getTrainingIds())));
        trainee.setTrainers(trainerDAO.getByIds(traineeRequestDTO.getTrainerIds()));
        return traineeMapper.toTraineeResponseDTO(traineeDAO.create(trainee));
    }

    public TraineeResponseDTO update(TraineeUpdateDTO traineeUpdateDTO) {
        log.info("Updating trainee: {}, {}",
                traineeUpdateDTO.getUser().getFirstName(),
                traineeUpdateDTO.getUser().getLastName());
        var trainee = traineeDAO.getById(traineeUpdateDTO.getId());
        if (trainee != null) {
            UserUpdateDTO userUpdateDTO = (UserUpdateDTO) traineeUpdateDTO.getUser();
            userUpdateDTO.setId(trainee.getUser().getId());
            User user = userMapper.toUser(userService.update(userUpdateDTO));
            trainee.setUser(user);
        }
        return traineeMapper.toTraineeResponseDTO(traineeDAO.update(trainee));
    }

    public TraineeResponseDTO getByUsername(String username) {
        log.info("Getting trainee by username: {}", username);
        return traineeMapper.toTraineeResponseDTO(traineeDAO.getByUsername(username));
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        log.info("Changing password for trainee with id: {}", changePasswordDTO.getId());
        var trainee = traineeDAO.getById(changePasswordDTO.getId());
        userService.changePassword(changePasswordDTO, trainee.getUser());
    }

    public void deleteByUsername(String username) {
        log.info("Deleting trainee by username: {}", username);
        traineeDAO.deleteByUsername(username);
    }

    public TraineeResponseDTO getById(Long traineeId) {
        log.info("Getting trainee with id: {}", traineeId);
        return traineeMapper.toTraineeResponseDTO(traineeDAO.getById(traineeId));
    }
}
