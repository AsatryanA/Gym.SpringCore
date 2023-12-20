package com.epam.gym.service.impl;

import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.dto.request.ToggleActiveDTO;
import com.epam.gym.entity.dto.request.TrainerRequestDTO;
import com.epam.gym.entity.dto.request.TrainerUpdateDTO;
import com.epam.gym.entity.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.entity.dto.response.TrainerResponseDTO;
import com.epam.gym.entity.dto.response.TrainerTrainingDTO;
import com.epam.gym.exception.ResourceCreationException;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.exception.VerificationException;
import com.epam.gym.mapper.TrainerMapper;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.service.TrainerService;
import com.epam.gym.service.TrainingTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerDAO trainerDAO;
    private final UserServiceImpl userServiceImpl;
    private final TrainingTypeService trainingTypeService;
    private final TrainerMapper trainerMapper;
    private final TrainingMapper trainingMapper;


    @Transactional
    public TrainerCreateResponseDTO create(TrainerRequestDTO trainerRequestDTO) {
        log.info("Creating trainer: {}, {}", trainerRequestDTO.getFirstName(), trainerRequestDTO.getLastName());
        trainingTypeService.getById(trainerRequestDTO.getSpecialization().getId());
        var user = userServiceImpl.create(trainerRequestDTO.getFirstName(), trainerRequestDTO.getLastName());
        var trainer = trainerDAO.create(trainerMapper.toTrainer(trainerRequestDTO, user))
                .orElseThrow(() -> new ResourceCreationException(Trainer.class));
        return trainerMapper.toTrainerCreateResponseDTO(trainer);
    }

    @Transactional
    public TrainerResponseDTO getById(Long id) {
        var trainer = trainerDAO.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, id));
        return trainerMapper.toTrainerResponseDTO(trainer);
    }

    public TrainerResponseDTO update(TrainerUpdateDTO trainerUpdateDTO) {
        log.info("Updating trainer with id: {}", trainerUpdateDTO.getId());
        var trainer = trainerDAO.getById(trainerUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, trainerUpdateDTO.getId()));
        var updatedTrainer = trainerDAO.update(trainerMapper.toTrainer(trainerUpdateDTO, trainer));
        return trainerMapper.toTrainerResponseDTO(updatedTrainer);
    }

    @Transactional
    public List<Trainer> getByIds(List<Long> trainerIds) {
        return trainerDAO.getByIds(trainerIds);
    }

    @Transactional
    public List<TrainerTrainingDTO> getTrainings(Long id) {
        var trainer = trainerDAO.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, id));
        return trainer.getTrainings().stream()
                .map(item -> trainingMapper.toTrainerTrainingDTO(item, trainer.getUser().getUsername()))
                .toList();
    }

    @Transactional
    public void toggleActive(ToggleActiveDTO toggleActiveDTO) {
        log.info("Activating trainer with id: {}", toggleActiveDTO.getId());
        var trainer = trainerDAO.getById(toggleActiveDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, toggleActiveDTO.getId()));
        if (Boolean.FALSE.equals(trainer.getUser().getIsActive())) {
            throw new VerificationException("This user account is currently deactivated. Please contact support for further assistance");
        }
        trainer.getUser().setIsActive(toggleActiveDTO.getIsActive());
        trainerDAO.update(trainer);
    }


}
