package com.epam.gym.mapper;

import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.dto.TraineeDTO;
import org.springframework.stereotype.Component;

@Component
public class TraineeMapper {
    public Trainee toTrainee(TraineeDTO traineeDTO, Long id) {
        return new Trainee(traineeDTO.getDateOfBirth(), traineeDTO.getAddress(), id);
    }
}
