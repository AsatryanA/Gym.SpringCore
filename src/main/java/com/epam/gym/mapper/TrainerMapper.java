package com.epam.gym.mapper;

import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.dto.TrainerDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {
    public Trainer toTrainer(TrainerDTO trainerDTO, Long id) {
        return new Trainer(trainerDTO.getSpecialization(), id);
    }
}
