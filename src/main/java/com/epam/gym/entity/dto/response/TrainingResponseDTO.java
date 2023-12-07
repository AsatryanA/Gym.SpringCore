package com.epam.gym.entity.dto.response;

import com.epam.gym.entity.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingResponseDTO {
    private String name;
    private LocalDate date;
    private Integer duration;
    private TrainingType trainingType;
}
