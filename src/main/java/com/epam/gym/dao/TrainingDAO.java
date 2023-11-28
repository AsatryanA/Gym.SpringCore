package com.epam.gym.dao;

import com.epam.gym.entity.Training;

import java.util.List;

public interface TrainingDAO {
    Training create(Training training);

    List<Training> getByIds(List<Long> ids);
}
