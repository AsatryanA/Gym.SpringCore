package com.epam.gym.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Training extends BaseEntity {

    Long traineeId;
    Long trainerId;
    String name;
    Long TypeId;
    Date date;
    Integer duration;
}
