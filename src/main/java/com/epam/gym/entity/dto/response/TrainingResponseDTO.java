package com.epam.gym.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingResponseDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private Integer duration;
    private TrainingTypeResponseDTO trainingType;
}
