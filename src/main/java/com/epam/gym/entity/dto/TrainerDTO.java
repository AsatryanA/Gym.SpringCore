package com.epam.gym.entity.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO extends BaseUserDTO {
    String specialization;
}
