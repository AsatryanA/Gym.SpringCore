package com.epam.gym.dao;

import com.epam.gym.entity.Trainee;

import java.util.List;

public interface TraineeDAO {
    Trainee create(Trainee trainee);


    Trainee update(Trainee trainee);

    Trainee getById(Long id);

    List<Trainee> getAll();

    Trainee getByUsername(String username);

    List<Trainee> getByIds(List<Long> ids);

    void deleteByUsername(String username);
}
