package com.epam.gym.service;

import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TraineeRequestDTO;
import com.epam.gym.model.dto.request.TraineeUpdateDTO;
import com.epam.gym.model.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.model.dto.response.TraineeResponseDTO;
import com.epam.gym.model.dto.response.TraineeTrainersResponseDTO;
import com.epam.gym.model.dto.response.TraineeTrainingDTO;

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
