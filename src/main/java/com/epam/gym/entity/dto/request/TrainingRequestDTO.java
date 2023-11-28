package com.epam.gym.entity.dto.request;

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
public class TrainingRequestDTO {
    private Long traineeId;
    private Long trainerId;
    private String name;
    private Long trainingTypeId;
    private LocalDate date;
    private Integer duration;
}
