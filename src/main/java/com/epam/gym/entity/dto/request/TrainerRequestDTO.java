package com.epam.gym.entity.dto.request;

import com.epam.gym.entity.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerRequestDTO {
    private TrainingType specialization;
    private UserRequestDTO user;
    private List<Long> trainingIds;
    private List<Long> traineeIds;
}
