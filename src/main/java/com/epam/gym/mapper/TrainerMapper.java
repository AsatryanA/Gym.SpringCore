package com.epam.gym.mapper;

import com.epam.gym.model.Trainee;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.User;
import com.epam.gym.model.dto.request.TrainerRequestDTO;
import com.epam.gym.model.dto.request.TrainerUpdateDTO;
import com.epam.gym.model.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.model.dto.response.TrainerResponseDTO;
import com.epam.gym.model.dto.response.TrainerTraineeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainerMapper {

    public Trainer toTrainer(TrainerRequestDTO trainerRequestDTO, User user) {
        return Trainer.builder()
                .user(user)
                .specialization(trainerRequestDTO.getSpecialization())
                .build();
    }

    public Trainer toTrainer(TrainerUpdateDTO trainerUpdateDTO, Trainer trainer) {
        trainer.getUser().setFirstName(trainerUpdateDTO.getFirstName());
        trainer.getUser().setLastName(trainerUpdateDTO.getLastName());
        trainer.getUser().setIsActive(trainerUpdateDTO.getIsActive());
        trainer.setSpecialization(trainerUpdateDTO.getSpecialization());
        return trainer;
    }

    public TrainerCreateResponseDTO toTrainerCreateResponseDTO(Trainer trainer) {
        return TrainerCreateResponseDTO.builder()
                .id(trainer.getId())
                .username(trainer.getUser().getUsername())
                .build();
    }

    public TrainerResponseDTO toTrainerResponseDTO(Trainer trainer) {
        return TrainerResponseDTO.builder()
                .isActive(trainer.getUser().getIsActive())
                .firstName(trainer.getUser().getFirstName())
                .lastName(trainer.getUser().getLastName())
                .specialization(trainer.getSpecialization())
                .trainees(trainer.getTrainees().stream()
                        .map(this::toTrainerTraineesResponseDTO)
                        .toList())
                .build();
    }

    private TrainerTraineeResponseDTO toTrainerTraineesResponseDTO(Trainee trainee) {
        return TrainerTraineeResponseDTO.builder()
                .firstName(trainee.getUser().getFirstName())
                .lastName(trainee.getUser().getLastName())
                .username(trainee.getUser().getUsername())
                .build();
    }
}
