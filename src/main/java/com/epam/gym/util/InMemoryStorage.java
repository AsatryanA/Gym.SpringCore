package com.epam.gym.util;

import com.epam.gym.entity.*;
import com.epam.gym.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.gym.util.Paths.*;

@Component
@Getter
@Setter
public class InMemoryStorage {
    private Map<Long, Trainer> trainerStorage = new HashMap<>();
    private Map<Long, Trainee> traineeStorage = new HashMap<>();
    private Map<Long, Training> trainingStorage = new HashMap<>();
    private Map<Long, TrainingType> trainingTypeStorage = new HashMap<>();
    private Map<Long, User> userStorage = new HashMap<>();

    @PostConstruct
    public void init() {
        traineeStorage = readJsonFile(Trainee.class, TRAINEE_DATA_PATH);
        trainerStorage = readJsonFile(Trainer.class, TRAINER_DATA_PATH);
        trainingStorage = readJsonFile(Training.class, TRAINING_DATA_PATH);
        trainingTypeStorage = readJsonFile(TrainingType.class, TRAINING_TYPE_DATA_PATH);
        userStorage = readJsonFile(User.class, USER_DATA_PATH);
    }

    private <T extends BaseEntity> Map<Long, T> readJsonFile(Class<T> model, String filePath) {
        var objectMapper = new ObjectMapper();
        var file = new File(filePath);
        try {
            List<T> o = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, model));
            Map<Long, T> map = new HashMap<>();
            o.forEach(t -> map.put(t.getId(), t));
            return map;
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file" + e);
        }
    }
}
