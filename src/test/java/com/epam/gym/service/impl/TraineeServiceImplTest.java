package com.epam.gym.service.impl;

import com.epam.gym.mapper.TraineeMapper;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.repository.TraineeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TraineeServiceImplTest {
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private AuthServiceImpl userServiceImpl;
    @Mock
    private TrainerServiceImpl trainerServiceImpl;
    @Mock
    private TraineeMapper traineeMapper;
    @Mock
    private TrainingMapper trainingMapper;
    @InjectMocks
    private TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenTrainee_whencreate_thenReturnTrainee() {

    }

    @Test
    void givenTraineeId_whenGetById_thenReturnTrainee() {
    }

    @Test
    void givenNonExistentId_whenGetById_thenReturnResourceNotFoundException() {
    }

    @Test
    void givenTrainee_whenUpdate_thenReturnUpdatedTrainee() {
    }

    @Test
    void givenTraineeId_whenDelete_thenNothing() {
    }

    @Test
    void updateTrainers() {
    }

    @Test
    void getTrainings() {
    }

    @Test
    void toggleActive() {
    }
}