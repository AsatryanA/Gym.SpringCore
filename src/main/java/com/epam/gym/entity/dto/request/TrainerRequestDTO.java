package com.epam.gym.entity.dto.request;

import com.epam.gym.entity.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerRequestDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private TrainingType specialization;

}
