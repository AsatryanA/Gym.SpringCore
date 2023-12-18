package com.epam.gym.service;

import com.epam.gym.entity.dto.request.ToggleActiveDTO;
import com.epam.gym.entity.dto.request.TraineeRequestDTO;
import com.epam.gym.entity.dto.request.TraineeUpdateDTO;
import com.epam.gym.entity.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.entity.dto.response.TraineeResponseDTO;
import com.epam.gym.entity.dto.response.TraineeTrainersResponseDTO;
import com.epam.gym.entity.dto.response.TraineeTrainingDTO;

import java.util.List;


public interface TraineeService {
    TraineeCreateResponseDTO create(TraineeRequestDTO traineeRequestDTO);

    TraineeResponseDTO getById(Long traineeId);

    TraineeResponseDTO update(TraineeUpdateDTO traineeUpdateDTO);

    void delete(Long id);

    List<TraineeTrainersResponseDTO> updateTrainers(Long id, List<Long> trainerIds);

    List<TraineeTrainingDTO> getTrainings(Long id);

    void toggleActive(ToggleActiveDTO toggleActiveDTO);
}
