package com.epam.gym.service.impl;

import com.epam.gym.model.Trainer;
import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TrainerRequestDTO;
import com.epam.gym.model.dto.request.TrainerUpdateDTO;
import com.epam.gym.model.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.model.dto.response.TrainerResponseDTO;
import com.epam.gym.model.dto.response.TrainerTrainingDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.exception.VerificationException;
import com.epam.gym.mapper.TrainerMapper;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.mapper.UserMapper;
import com.epam.gym.repository.TrainerRepository;
import com.epam.gym.service.TrainerService;
import com.epam.gym.service.TrainingTypeService;
import com.epam.gym.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserService userService;
    private final TrainingTypeService trainingTypeService;
    private final TrainerMapper trainerMapper;
    private final TrainingMapper trainingMapper;
    private final UserMapper userMapper;


    @Transactional
    public TrainerCreateResponseDTO create(TrainerRequestDTO trainerRequestDTO) {
        trainingTypeService.getById(trainerRequestDTO.getSpecialization().getId());
        var userWithRawPassword = userService.create(trainerRequestDTO.getFirstName(), trainerRequestDTO.getLastName());
        var user = userMapper.toUser(userWithRawPassword);
        var trainer = trainerRepository.save(trainerMapper.toTrainer(trainerRequestDTO, user));
        var trainerCreateResponseDTO = trainerMapper.toTrainerCreateResponseDTO(trainer);
        trainerCreateResponseDTO.setPassword(userWithRawPassword.getRawPassword());
        return trainerCreateResponseDTO;
    }

    @Transactional
    public TrainerResponseDTO getById(Long id) {
        var trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, id));
        return trainerMapper.toTrainerResponseDTO(trainer);
    }

    public TrainerResponseDTO update(TrainerUpdateDTO trainerUpdateDTO) {
        var trainer = trainerRepository.findById(trainerUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, trainerUpdateDTO.getId()));
        var updatedTrainer = trainerRepository.save(trainerMapper.toTrainer(trainerUpdateDTO, trainer));
        return trainerMapper.toTrainerResponseDTO(updatedTrainer);
    }

    @Transactional
    public List<Trainer> getByIds(List<Long> trainerIds) {
        return trainerRepository.findAllById(trainerIds);
    }

    @Transactional
    public List<TrainerTrainingDTO> getTrainings(Long id) {
        var trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, id));
        return trainer.getTrainings().stream()
                .map(item -> trainingMapper.toTrainerTrainingDTO(item, trainer.getUser().getUsername()))
                .toList();
    }

    @Transactional
    public void toggleActive(ToggleActiveDTO toggleActiveDTO) {
        var trainer = trainerRepository.findById(toggleActiveDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, toggleActiveDTO.getId()));
        if (Boolean.FALSE.equals(trainer.getUser().getIsActive())) {
            throw new VerificationException("This user account is currently deactivated. Please contact support for further assistance");
        }
        trainer.getUser().setIsActive(toggleActiveDTO.getIsActive());
        trainerRepository.save(trainer);
    }


}
