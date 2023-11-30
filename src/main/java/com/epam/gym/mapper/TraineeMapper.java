package com.epam.gym.mapper;

import com.epam.gym.entity.BaseEntity;
import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.TraineeRequestDTO;
import com.epam.gym.entity.dto.response.TraineeResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraineeMapper {

    private final UserMapper userMapper;

    public Trainee toTrainee(TraineeRequestDTO traineeRequestDTO, User user) {
        if (traineeRequestDTO == null || user == null) {
            throw new EntityNotFoundException("Trainee not found");
        }
        return Trainee.builder()
                .dateOfBirth(traineeRequestDTO.getDateOfBirth())
                .user(user)
                .address(traineeRequestDTO.getAddress())
                .build();
    }

    public TraineeResponseDTO toTraineeResponseDTO(Trainee trainee) {
        if (trainee == null) {
            throw new EntityNotFoundException("Trainee not found");
        }
        return TraineeResponseDTO.builder()
                .id(trainee.getId())
                .dateOfBirth(trainee.getDateOfBirth())
                .address(trainee.getAddress())
                .user(userMapper.toUserResponseDto(trainee.getUser()))
                .trainingIds(trainee.getTrainings().stream().map(BaseEntity::getId).toList())
                .trainerIds(trainee.getTrainers().stream().map(BaseEntity::getId).toList())
                .build();
    }
}


