package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.TraineeRequestDTO;
import com.epam.gym.entity.dto.request.TraineeTrainersUpdateDTO;
import com.epam.gym.entity.dto.response.TraineeResponseDTO;
import com.epam.gym.entity.dto.response.TrainingResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import com.epam.gym.mapper.TraineeMapper;
import com.epam.gym.mapper.TrainingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TraineeService {

    private final TraineeDAO traineeDAO;
    private final TrainingDAO trainingDAO;
    private final TrainerDAO trainerDAO;
    private final UserService userService;
    private final TraineeMapper traineeMapper;
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;


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

/*    public TraineeResponseDTO update(TraineeUpdateDTO traineeUpdateDTO) {
        return null;
    }*/

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
        var trainee = traineeDAO.getByUsername(username);
        trainee.getTrainers().forEach(trainer -> trainer.getTrainees().remove(trainee));
        trainee.getTrainings().clear();
        trainee.getTrainers().clear();
        traineeDAO.delete(trainee);
    }

    public TraineeResponseDTO getById(Long traineeId) {
        log.info("Getting trainee with id: {}", traineeId);
        return traineeMapper.toTraineeResponseDTO(traineeDAO.getById(traineeId));
    }

    public void activate(Long id, boolean isActive) {
        log.info("Activating trainee with id: {}", id);
        var trainee = traineeDAO.getById(id);
        if (trainee != null) {
            trainee.getUser().setIsActive(isActive);
            traineeDAO.update(trainee);
        }
    }

    public TraineeResponseDTO updateTrainers(TraineeTrainersUpdateDTO traineeTrainersUpdateDTO) {
        log.info("Updating trainers for trainee");
        var trainee = traineeDAO.getById(traineeTrainersUpdateDTO.getTraineeId());
        if (trainee == null) {
            throw new EntityNotFoundException("Trainee not found");
        }
        trainee.setTrainers(trainerDAO.getByIds(traineeTrainersUpdateDTO.getTrainerIds()));
        return traineeMapper.toTraineeResponseDTO(traineeDAO.update(trainee));
    }

    public List<TrainingResponseDTO> getTrainingsByUsernameAndDuration(String username, Integer durationFrom, Integer durationTo) {
        return trainingService.getTrainingsByUsernameAndDuration(username, durationFrom, durationTo);
    }

    public TraineeResponseDTO findByUsernameAndPassword(String username, String password) {
        log.info("Getting trainee by username: {}", username);
        var trainee = traineeDAO.findByUsernameAndPassword(username, password);
        if (trainee == null) {
            throw new EntityNotFoundException("Trainee not found");
        }
        return traineeMapper.toTraineeResponseDTO(trainee);
    }
}