package com.epam.gym.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeLoginDTO {
    @NotNull
    Long id;
    @NotNull
    String oldPassword;
    @NotNull
    String newPassword;
}
