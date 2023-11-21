package com.epam.gym.entity;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Trainer extends BaseEntity {

    String specialization;
    Long userId;

}

