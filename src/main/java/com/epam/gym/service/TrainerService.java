package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.TrainerRequestDTO;
import com.epam.gym.entity.dto.request.TrainerUpdateDTO;
import com.epam.gym.entity.dto.request.UserUpdateDTO;
import com.epam.gym.entity.dto.response.TrainerResponseDTO;
import com.epam.gym.mapper.TrainerMapper;
import com.epam.gym.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerDAO trainerDAO;
    private final TraineeDAO traineeDAO;
    private final TrainingDAO trainingDAO;
    private final UserService userService;
    private final TrainerMapper trainerMapper;
    private final UserMapper userMapper;

    public TrainerResponseDTO create(TrainerRequestDTO trainerRequestDTO) {
        log.info("Creating trainer: {}, {}",
                trainerRequestDTO.getUser().getFirstName(),
                trainerRequestDTO.getUser().getLastName());
        var user = userService.create(trainerRequestDTO.getUser());
        var trainer = trainerMapper.toTrainer(trainerRequestDTO, user);
        trainer.setTrainees(traineeDAO.getByIds(trainerRequestDTO.getTraineeIds()));
        trainer.setTrainings(trainingDAO.getByIds(trainerRequestDTO.getTrainingIds()));
        return trainerMapper.toTrainerResponseDTO(trainerDAO.create(trainer));
    }

    public TrainerResponseDTO update(TrainerUpdateDTO trainerUpdateDTO) {
        log.info("Updating trainer: {}, {}",
                trainerUpdateDTO.getUser().getFirstName(),
                trainerUpdateDTO.getUser().getLastName());
        var trainer = trainerDAO.getById(trainerUpdateDTO.getId());
        if (trainer != null) {
            UserUpdateDTO userUpdateDTO = (UserUpdateDTO) trainerUpdateDTO.getUser();
            userUpdateDTO.setId(trainer.getUser().getId());
            User user = userMapper.toUser(userService.update(userUpdateDTO));
            trainer.setUser(user);
        }
        return trainerMapper.toTrainerResponseDTO(trainerDAO.update(trainer));
    }

    public TrainerResponseDTO getByUsername(String username) {
        log.info("Getting trainee by username: {}", username);
        return trainerMapper.toTrainerResponseDTO(trainerDAO.getByUsername(username));
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        log.info("Changing password for trainee with id: {}", changePasswordDTO.getId());
        var trainer = trainerDAO.getById(changePasswordDTO.getId());
        userService.changePassword(changePasswordDTO, trainer.getUser());
    }


    public TrainerResponseDTO getById(Long id) {
        log.info("Getting trainer with id: {}", id);

        return trainerMapper.toTrainerResponseDTO(trainerDAO.getById(id));
    }

}
