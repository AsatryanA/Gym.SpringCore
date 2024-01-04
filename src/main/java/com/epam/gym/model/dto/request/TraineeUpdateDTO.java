package com.epam.gym.model.dto.request;

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
public class TraineeUpdateDTO extends TraineeRequestDTO {
    @NotNull
    private Long id;
    private Boolean isActive;
}
