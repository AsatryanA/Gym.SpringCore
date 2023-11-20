package com.epam.gym.entity.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
    Long traineeId;
    Long trainerId;
    String name;
    Long TypeId;
    Date date;
    Integer duration;
}
