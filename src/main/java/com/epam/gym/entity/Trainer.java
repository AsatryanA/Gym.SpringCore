package com.epam.gym.entity;

import com.epam.gym.entity.baseEntity.BaseEntity;
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

