package com.epam.gym.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeRequestDTO {
    private LocalDate dateOfBirth;
    private String address;
    private UserRequestDTO user;
    private List<Long> trainerIds;
    private List<Long> trainingIds;
}
