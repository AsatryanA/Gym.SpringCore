package com.epam.gym.service.impl;

import com.epam.gym.dao.TrainingTypeDAO;
import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.mapper.TrainingTypeMapper;
import com.epam.gym.service.TrainingTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeDAO trainingTypeDAO;
    private final TrainingTypeMapper trainingTypeMapper;

    @Transactional(readOnly = true)
    public List<TrainingTypeResponseDTO> getAll() {
        log.info("Getting all training types");
        return trainingTypeDAO.getAll().stream().map(trainingTypeMapper::toTrainingTypeResponseDto).toList();
    }

    @Override
    public TrainingType getById(Long id) {
        return trainingTypeDAO.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TrainingType.class, id)
                );
    }
}
