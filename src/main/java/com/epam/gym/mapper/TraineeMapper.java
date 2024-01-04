package com.epam.gym.mapper;

import com.epam.gym.model.Trainee;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.User;
import com.epam.gym.model.dto.request.TraineeRequestDTO;
import com.epam.gym.model.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.model.dto.response.TraineeResponseDTO;
import com.epam.gym.model.dto.response.TraineeTrainersResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraineeMapper {

    public Trainee toTrainee(TraineeRequestDTO traineeRequestDTO, User user) {
        return Trainee.builder()
                .dateOfBirth(traineeRequestDTO.getDateOfBirth())
                .user(user)
                .address(traineeRequestDTO.getAddress())
                .build();
    }

    public Trainee toTrainee(TraineeRequestDTO traineeRequestDTO, Trainee trainee) {
        trainee.setDateOfBirth(traineeRequestDTO.getDateOfBirth());
        trainee.setAddress(traineeRequestDTO.getAddress());
        trainee.getUser().setFirstName(traineeRequestDTO.getFirstName());
        trainee.getUser().setLastName(traineeRequestDTO.getLastName());
        return trainee;
    }

    public TraineeCreateResponseDTO toTraineeCreateResponseDTO(Trainee trainee) {
        return TraineeCreateResponseDTO.builder()
                .id(trainee.getId())
                .username(trainee.getUser().getUsername())
                .build();
    }

    public TraineeResponseDTO toTraineeResponseDTO(Trainee trainee) {
        return TraineeResponseDTO.builder()
                .firstName(trainee.getUser().getFirstName())
                .lastName(trainee.getUser().getLastName())
                .dateOfBirth(trainee.getDateOfBirth())
                .address(trainee.getAddress())
                .trainers(trainee.getTrainers().stream().map(this::toTraineeTrainersResponseDTO).toList())
                .build();
    }

    public TraineeTrainersResponseDTO toTraineeTrainersResponseDTO(Trainer trainer) {
        return TraineeTrainersResponseDTO.builder()
                .firstName(trainer.getUser().getFirstName())
                .lastName(trainer.getUser().getLastName())
                .username(trainer.getUser().getUsername())
                .specialization(trainer.getSpecialization())
                .build();
    }
}


