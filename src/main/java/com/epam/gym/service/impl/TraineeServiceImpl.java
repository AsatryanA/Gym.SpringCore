package com.epam.gym.service.impl;

import com.epam.gym.model.Trainee;
import com.epam.gym.model.User;
import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TraineeRequestDTO;
import com.epam.gym.model.dto.request.TraineeUpdateDTO;
import com.epam.gym.model.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.model.dto.response.TraineeResponseDTO;
import com.epam.gym.model.dto.response.TraineeTrainersResponseDTO;
import com.epam.gym.model.dto.response.TraineeTrainingDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.exception.VerificationException;
import com.epam.gym.mapper.TraineeMapper;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.mapper.UserMapper;
import com.epam.gym.repository.TraineeRepository;
import com.epam.gym.service.TraineeService;
import com.epam.gym.service.TrainerService;
import com.epam.gym.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {

    private final UserService userService;
    private final TrainerService trainerService;
    private final TraineeRepository traineeRepository;
    private final TraineeMapper traineeMapper;
    private final TrainingMapper trainingMapper;
    private final UserMapper userMapper;


    @Transactional
    public TraineeCreateResponseDTO create(TraineeRequestDTO traineeRequestDTO) {
        var userWithRawPassword = userService.create(traineeRequestDTO.getFirstName(), traineeRequestDTO.getLastName());
        User user = userMapper.toUser(userWithRawPassword);
        var trainee = traineeRepository.save(traineeMapper.toTrainee(traineeRequestDTO, user));
        var traineeCreateResponseDTO = traineeMapper.toTraineeCreateResponseDTO(trainee);
        traineeCreateResponseDTO.setPassword(userWithRawPassword.getRawPassword());
        return traineeCreateResponseDTO;
    }

    @Transactional
    public TraineeResponseDTO getById(Long traineeId) {
        var trainee = traineeRepository.findById(traineeId)
                .orElseThrow(() -> new ResourceNotFoundException(Trainee.class, traineeId));
        return traineeMapper.toTraineeResponseDTO(trainee);
    }

    @Transactional
    public TraineeResponseDTO update(TraineeUpdateDTO traineeUpdateDTO) {
        var trainee = traineeRepository.findById(traineeUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainee.class, traineeUpdateDTO.getId()));
        var updatedTrainee = traineeRepository.save(traineeMapper.toTrainee(traineeUpdateDTO, trainee));
        return traineeMapper.toTraineeResponseDTO(updatedTrainee);
    }

    @Transactional
    public void delete(Long id) {
        var trainee = traineeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Trainee.class, id));
        trainee.getTrainers().forEach(trainer -> trainer.getTrainees().remove(trainee));
        trainee.getTrainers().clear();
        traineeRepository.delete(trainee);
    }

    @Transactional
    public List<TraineeTrainersResponseDTO> updateTrainers(Long id, List<Long> trainerIds) {
        var trainee = traineeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Trainee.class, id));
        trainee.setTrainers(trainerService.getByIds(trainerIds));
        var updatedTrainee = traineeRepository.save(trainee);
        return updatedTrainee.getTrainers().stream().map(traineeMapper::toTraineeTrainersResponseDTO).toList();
    }

    @Transactional
    public List<TraineeTrainingDTO> getTrainings(Long id) {
        var trainee = traineeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Trainee.class, id));
        return trainee.getTrainings().stream().map(item -> trainingMapper.toTraineeTrainingDTO(item, trainee.getUser().getUsername())).toList();
    }

    @Transactional
    public void toggleActive(ToggleActiveDTO toggleActiveDTO) {
        var trainee = traineeRepository.findById(toggleActiveDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainee.class, toggleActiveDTO.getId()));
        if (Boolean.FALSE.equals(trainee.getUser().getIsActive())) {
            throw new VerificationException("This user account is currently deactivated. Please contact support for further assistance");
        }
        trainee.getUser().setIsActive(toggleActiveDTO.getIsActive());
        traineeRepository.save(trainee);
    }
}