package com.epam.gym.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    String firstName;
    String lastName;
    String username;
    String password;
    Boolean isActive;
}
