package com.epam.gym.service.impl;

import com.epam.gym.model.TrainingType;
import com.epam.gym.model.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.mapper.TrainingTypeMapper;
import com.epam.gym.repository.TrainingTypeRepository;
import com.epam.gym.service.TrainingTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeMapper trainingTypeMapper;

    @Transactional
    public List<TrainingTypeResponseDTO> getAll(Pageable pageable) {
        return trainingTypeRepository.findAll(pageable).stream().map(trainingTypeMapper::toTrainingTypeResponseDto).toList();
    }

    @Override
    public TrainingType getById(Long id) {
        return trainingTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TrainingType.class, id)
                );
    }
}
