package com.epam.gym.controller;

import com.epam.gym.entity.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.service.TrainingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/training-types")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;

    @GetMapping
    ResponseEntity<List<TrainingTypeResponseDTO>> getAll(@RequestParam @Positive Integer size,
                                                         @RequestParam Integer page) {
        return new ResponseEntity<>(trainingTypeService.getAll(Pageable.ofSize(size).withPage(page)), HttpStatus.OK);
    }
}
