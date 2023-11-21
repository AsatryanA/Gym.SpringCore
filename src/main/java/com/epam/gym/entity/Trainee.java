package com.epam.gym.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Trainee extends BaseEntity {

    Date dateOfBirth;
    String address;
    Long userId;
}
