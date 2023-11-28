package com.epam.gym.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private List<Long> traineeIds;
    private List<Long> trainingIds;
    private TrainingTypeResponseDTO specialization;
}
