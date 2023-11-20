package com.epam.gym.mapper;

import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.dto.TraineeDTO;

public class TraineeMapper {


    public static Trainee toTrainee(TraineeDTO traineeDTO, Long id) {
        return new Trainee(traineeDTO.getDateOfBirth(), traineeDTO.getAddress(), id);
    }


}
