package com.epam.gym.controller;

import com.epam.gym.entity.dto.request.ToggleActiveDTO;
import com.epam.gym.entity.dto.request.TraineeRequestDTO;
import com.epam.gym.entity.dto.request.TraineeUpdateDTO;
import com.epam.gym.entity.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.entity.dto.response.TraineeResponseDTO;
import com.epam.gym.entity.dto.response.TraineeTrainersResponseDTO;
import com.epam.gym.entity.dto.response.TraineeTrainingDTO;
import com.epam.gym.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    @PostMapping
    ResponseEntity<TraineeCreateResponseDTO> create(@Valid @RequestBody TraineeRequestDTO traineeRequestDTO) {
        return new ResponseEntity<>(traineeService.create(traineeRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<TraineeResponseDTO> getById(@Valid @RequestParam Long id) {
        return new ResponseEntity<>(traineeService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<TraineeResponseDTO> update(@Valid @RequestBody TraineeUpdateDTO traineeUpdateDTO) {
        return new ResponseEntity<>(traineeService.update(traineeUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Valid @RequestParam Long id) {
        traineeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/trainers")
    ResponseEntity<List<TraineeTrainersResponseDTO>> updateTrainers(@Valid @RequestParam Long id, @Valid @RequestBody List<Long> trainerIds) {
        return new ResponseEntity<>(traineeService.updateTrainers(id, trainerIds), HttpStatus.OK);
    }

    @GetMapping("/{id}/trainings")
    ResponseEntity<List<TraineeTrainingDTO>> getTrainings(@Valid @RequestParam Long id) {
        return new ResponseEntity<>(traineeService.getTrainings(id), HttpStatus.OK);
    }

    @PatchMapping("/toggle-active")
    ResponseEntity<Void> toggleActive(@Valid @RequestBody ToggleActiveDTO toggleActiveDTO) {
        traineeService.toggleActive(toggleActiveDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}