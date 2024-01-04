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
public class TrainerUpdateDTO extends TrainerRequestDTO {
    @NotNull
    private Long id;
    @NotNull
    private Boolean isActive;
}
