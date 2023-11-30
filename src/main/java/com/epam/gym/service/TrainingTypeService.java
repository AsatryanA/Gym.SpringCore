package com.epam.gym.service;

import com.epam.gym.dao.TrainingTypeDAO;
import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.mapper.TrainingTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TrainingTypeService {

    private final TrainingTypeDAO trainingTypeDAO;
    private final TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeResponseDTO getById(Long id) {
        log.info("Getting training type with id: {}", id);
        return trainingTypeMapper.toTrainingTypeResponseDto(trainingTypeDAO.getById(id));
    }

    public List<TrainingTypeResponseDTO> getAll() {
        log.info("Getting all training types");
        return trainingTypeDAO.getAll().stream()
                .map(trainingTypeMapper::toTrainingTypeResponseDto).toList();
    }
}
