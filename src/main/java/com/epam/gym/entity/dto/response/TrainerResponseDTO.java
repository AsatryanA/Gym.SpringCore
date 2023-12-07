package com.epam.gym.entity.dto.response;

import com.epam.gym.entity.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerResponseDTO {
    private String firstName;
    private String lastName;
    private TrainingType specialization;
    private boolean isActive;
    private List<TrainerTraineeResponseDTO> trainees;
}
