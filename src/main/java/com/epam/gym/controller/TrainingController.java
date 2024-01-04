package com.epam.gym.controller;

import com.epam.gym.model.dto.request.TrainingRequestDTO;
import com.epam.gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody TrainingRequestDTO trainingRequestDTO) {
        trainingService.create(trainingRequestDTO);
        return ResponseEntity.ok().build();
    }
}
