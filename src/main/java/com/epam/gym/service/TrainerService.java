package com.epam.gym.service;

import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.dto.TrainerDTO;
import com.epam.gym.mapper.TrainerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainerService {
    private final TrainerDAO trainerDAO;
    private final UserService userService;

    @Autowired
    public TrainerService(TrainerDAO trainerDAO, UserService userService) {
        this.trainerDAO = trainerDAO;
        this.userService = userService;
    }

    public Trainer createTrainer(TrainerDTO trainerDTO) {
        log.info("Creating trainer: {}", trainerDTO);
        var user = userService.createUser(trainerDTO);
        var trainer = TrainerMapper.toTrainer(trainerDTO, user.getId());
        return trainerDAO.createTrainer(trainer);
    }

    public Trainer getTrainerById(Long id) {
        log.info("Getting trainer by id: {}", id);
        return trainerDAO.getTrainerById(id);
    }

    public Trainer updateTrainer(TrainerDTO trainerDTO) {
        log.info("Updating trainer: {}", trainerDTO);
        var user = userService.createUser(trainerDTO);
        var trainer = TrainerMapper.toTrainer(trainerDTO, user.getId());
        return trainerDAO.updateTrainer(trainer);
    }
}
