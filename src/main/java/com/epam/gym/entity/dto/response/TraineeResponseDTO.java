package com.epam.gym.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraineeResponseDTO {
    private Long id;
    private String address;
    private UserResponseDTO user;
    private LocalDate dateOfBirth;
    private List<Long> trainerIds;
    private List<Long> trainingIds;
}
