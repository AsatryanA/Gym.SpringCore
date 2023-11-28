package com.epam.gym.mapper;

import com.epam.gym.entity.BaseEntity;
import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.TrainerRequestDTO;
import com.epam.gym.entity.dto.response.TrainerResponseDTO;
import com.epam.gym.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainerMapper {
    private final TrainingTypeMapper trainingTypeMapper;
    private final UserMapper userMapper;

    public Trainer toTrainer(TrainerRequestDTO trainerRequestDTO, User user) {
        if (trainerRequestDTO == null || user == null) {
            throw new EntityNotFoundException("Trainer not found");
        }
        return Trainer.builder()
                .user(user)
                .specialization(trainerRequestDTO.getSpecialization())
                .build();
    }

    public TrainerResponseDTO toTrainerResponseDTO(Trainer trainer) {
        if (trainer == null) {
            throw new EntityNotFoundException("Trainer not found");
        }
        return TrainerResponseDTO.builder()
                .id(trainer.getId())
                .specialization(trainingTypeMapper.toTrainingTypeResponseDto(trainer.getSpecialization()))
                .user(userMapper.toUserResponseDto(trainer.getUser()))
                .traineeIds(trainer.getTrainees().stream().map(BaseEntity::getId).toList())
                .trainingIds(trainer.getTrainings().stream().map(BaseEntity::getId).toList())
                .build();
    }

    public Trainer toTrainer(TrainerResponseDTO trainer) {
        if (trainer == null) {
            throw new EntityNotFoundException(" Trainer not found");
        }
        return Trainer.builder()
                .id(trainer.getId())
                .specialization(trainingTypeMapper.toTrainingType(trainer.getSpecialization()))
                .user(userMapper.toUser(trainer.getUser()))
                .build();
    }
}
