package com.epam.gym.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequestDTO {
    @NotNull
    private Long traineeId;
    @NotNull
    private Long trainerId;
    @NotNull
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull
    @Positive
    private Integer duration;
}
