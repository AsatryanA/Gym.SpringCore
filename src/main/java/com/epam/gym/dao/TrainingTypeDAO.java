package com.epam.gym.dao;

import com.epam.gym.entity.TrainingType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeDAO {

    List<TrainingType> getAll(Pageable pageable);

    Optional<TrainingType> getById(Long id);

}
