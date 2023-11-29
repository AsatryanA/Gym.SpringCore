package com.epam.gym.dao;

import com.epam.gym.entity.Trainee;

import java.util.List;

public interface TraineeDAO {
    Trainee create(Trainee trainee);

    Trainee update(Trainee trainee);

    Trainee getById(Long id);

    Trainee getByUsername(String username);

    List<Trainee> getByIds(List<Long> ids);

    void delete(Trainee trainee);

    Trainee findByUsernameAndPassword(String username, String password);

}
