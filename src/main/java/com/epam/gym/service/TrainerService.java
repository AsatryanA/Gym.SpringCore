package com.epam.gym.service;

import com.epam.gym.model.Trainer;
import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TrainerRequestDTO;
import com.epam.gym.model.dto.request.TrainerUpdateDTO;
import com.epam.gym.model.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.model.dto.response.TrainerResponseDTO;
import com.epam.gym.model.dto.response.TrainerTrainingDTO;

import java.util.List;

public interface TrainerService {

    TrainerCreateResponseDTO create(TrainerRequestDTO trainerRequestDTO);

    TrainerResponseDTO getById(Long id);

    TrainerResponseDTO update(TrainerUpdateDTO trainerUpdateDTO);

    List<Trainer> getByIds(List<Long> trainerIds);

    List<TrainerTrainingDTO> getTrainings(Long id);

    void toggleActive(ToggleActiveDTO toggleActiveDTO);

}
