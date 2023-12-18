package com.epam.gym.service;

import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.dto.request.ToggleActiveDTO;
import com.epam.gym.entity.dto.request.TrainerRequestDTO;
import com.epam.gym.entity.dto.request.TrainerUpdateDTO;
import com.epam.gym.entity.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.entity.dto.response.TrainerResponseDTO;
import com.epam.gym.entity.dto.response.TrainerTrainingDTO;

import java.util.List;

public interface TrainerService {

    TrainerCreateResponseDTO create(TrainerRequestDTO trainerRequestDTO);

    TrainerResponseDTO getById(Long id);

    TrainerResponseDTO update(TrainerUpdateDTO trainerUpdateDTO);

    List<Trainer> getByIds(List<Long> trainerIds);

    List<TrainerTrainingDTO> getTrainings(Long id);

    void toggleActive(ToggleActiveDTO toggleActiveDTO);

}
