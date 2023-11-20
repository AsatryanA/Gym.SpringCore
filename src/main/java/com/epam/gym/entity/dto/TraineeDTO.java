package com.epam.gym.entity.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDTO extends BaseUserDTO {
    Date dateOfBirth;
    String address;
}
