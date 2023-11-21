package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.dto.TraineeDTO;
import com.epam.gym.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringJUnitConfig(classes = SpringTestConfig.class)
@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private UserService userService;

    @InjectMocks
    private TraineeService traineeService;

    @Test
    void createTrainee_ShouldCreateTrainee() {

        var traineeDTO = new TraineeDTO(new Date(), "some address");
        traineeDTO.setFirstName("John");
        traineeDTO.setLastName("Doe");

        var user = UserMapper.toUser(traineeDTO);
        user.setId(11L);

        var trainee = new Trainee(traineeDTO.getDateOfBirth(), traineeDTO.getAddress(), user.getId());

        when(userService.createUser(any(TraineeDTO.class))).thenReturn(user);
        when(traineeDAO.create(any(Trainee.class))).thenReturn(trainee);

        Trainee createdTrainee = traineeService.createTrainee(traineeDTO);

        assertEquals(user.getId(), createdTrainee.getUserId());
        verify(userService, Mockito.times(1)).createUser(traineeDTO);
        verify(traineeDAO, Mockito.times(1)).create(any(Trainee.class));
    }

    @Test
    void getTraineeById_ShouldReturnTrainee() {

        long traineeId = 1L;
        Trainee expectedTrainee = new Trainee(new Date(), "John Doe", 1L);

        when(traineeDAO.getById(traineeId)).thenReturn(expectedTrainee);

        Trainee retrievedTrainee = traineeService.getTraineeById(traineeId);

        assertEquals(expectedTrainee, retrievedTrainee);
        verify(traineeDAO, Mockito.times(1)).getById(traineeId);
    }

    @Test
    void deleteTrainee_ShouldDeleteTrainee() {

        long traineeId = 1L;

        traineeService.deleteTrainee(traineeId);

        verify(traineeDAO, Mockito.times(1)).delete(traineeId);
    }


}