/*
package com.epam.gym.service;

import com.epam.gym.dao.TraineeDAO;
import com.epam.gym.dao.TrainerDAO;
import com.epam.gym.dao.TrainingDAO;
import com.epam.gym.entity.Trainee;
import com.epam.gym.entity.Trainer;
import com.epam.gym.entity.Training;
import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.User;
import com.epam.gym.entity.dto.request.ChangePasswordDTO;
import com.epam.gym.entity.dto.request.TraineeRequestDTO;
import com.epam.gym.entity.dto.request.TraineeTrainersUpdateDTO;
import com.epam.gym.entity.dto.request.UserRequestDTO;
import com.epam.gym.entity.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.entity.dto.response.TrainingResponseDTO;
import com.epam.gym.entity.dto.response.UserResponseDTO;
import com.epam.gym.mapper.TraineeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private TrainingDAO trainingDAO;

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private TraineeMapper traineeMapper;

    @InjectMocks
    private TraineeService traineeService;

    @Test
    void testCreateTrainee() {
        var traineeRequestDTO = createTestTraineeRequestDTO();
        var trainee = createTestTrainee();
        var user = createTestUser();
        when(userService.create(traineeRequestDTO.getUser())).thenReturn(user);
        when(traineeMapper.toTrainee(traineeRequestDTO, user)).thenReturn(createTestTrainee());
        when(trainingDAO.getByIds(traineeRequestDTO.getTrainingIds())).thenReturn(createTestTrainings());
        when(trainerDAO.getByIds(traineeRequestDTO.getTrainerIds())).thenReturn(createTestTrainers());
        when(traineeDAO.create(trainee)).thenReturn(trainee);
        TraineeCreateResponseDTO result = traineeService.create(traineeRequestDTO);
        assertNotNull(result);
        assertEquals(trainee.getId(), result.getId());
        assertEquals(trainee.getAddress(), result.getAddress());
        assertEquals(trainee.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(trainee.getUser().getId(), result.getUser().getId());
    }

    @Test
    void testGetByUsername() {
        var username = "Test.Username";
        var trainee = createTestTrainee();
        when(traineeDAO.getByUsername(username)).thenReturn(trainee);
        when(traineeMapper.toTraineeResponseDTO(trainee)).thenReturn(createTestTraineeResponseDTO(trainee));
        var result = traineeService.getByUsername(username);
        assertNotNull(result);
    }

    @Test
    void testChangePassword() {
        var changePasswordDTO = createTestChangePasswordDTO();
        var trainee = createTestTrainee();
        when(traineeDAO.getById(changePasswordDTO.getId())).thenReturn(trainee);
        assertDoesNotThrow(() -> traineeService.changePassword(changePasswordDTO));
    }

    @Test
    void testDeleteByUsername() {
        var username = "testUsername";
        var trainee = createTestTrainee();
        var trainer = createTestTrainer();
        trainee.setTrainers(Collections.singletonList(trainer));
        when(traineeDAO.getByUsername(username)).thenReturn(trainee);
        assertDoesNotThrow(() -> traineeService.deleteByUsername(username));
    }

    @Test
    void testGetById() {
        var traineeId = 1L;
        var trainee = createTestTrainee();
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);
        when(traineeMapper.toTraineeResponseDTO(trainee)).thenReturn(createTestTraineeResponseDTO(trainee));
        var result = traineeService.getById(traineeId);
        assertNotNull(result);
    }

    @Test
    void testActivate() {
        var traineeId = 1L;
        boolean isActive = true;
        var trainee = createTestTrainee();
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);
        assertDoesNotThrow(() -> traineeService.activate(traineeId, isActive));
    }

    @Test
    void testUpdateTrainers() {
        var updateDTO = createTestTraineeTrainersUpdateDTO();
        var trainee = createTestTrainee();
        when(traineeDAO.getById(updateDTO.getTraineeId())).thenReturn(trainee);
        when(trainerDAO.getByIds(updateDTO.getTrainerIds())).thenReturn(createTestTrainers());
        TraineeCreateResponseDTO result = traineeService.updateTrainers(updateDTO);
        assertNotNull(result);
    }

    private Trainee createTestTrainee() {
        return Trainee.builder()
                .id(1L)
                .address("Test Address")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .user(createTestUser())
                .trainings(createTestTrainings())
                .trainers(createTestTrainers())
                .build();
    }

    private Trainer createTestTrainer() {
        return Trainer.builder()
                .id(1L)
                .specialization(createTestTrainingType())
                .user(createTestUser())
                .trainings(createTestTrainings())
                .trainees(createTestTrainees())
                .build();
    }

    private TraineeCreateResponseDTO createTestTraineeResponseDTO(Trainee trainee) {
        return TraineeCreateResponseDTO.builder()
                .id(trainee.getId())
                .address(trainee.getAddress())
                .dateOfBirth(trainee.getDateOfBirth())
                .user(createTestUserResponseDTO(trainee.getUser()))
                .trainerIds(Arrays.asList(1L, 2L))
                .trainingIds(Arrays.asList(1L, 2L))
                .build();
    }

    private ChangePasswordDTO createTestChangePasswordDTO() {
        return ChangePasswordDTO.builder()
                .id(1L)
                .newPassword("newPassword")
                .oldPassword("oldPassword")
                .build();
    }

    private TraineeTrainersUpdateDTO createTestTraineeTrainersUpdateDTO() {
        return TraineeTrainersUpdateDTO.builder()
                .traineeId(1L)
                .trainerIds(Arrays.asList(1L, 2L))
                .build();
    }

    private List<TrainingResponseDTO> createTestTrainingResponseDTOs() {
        return Arrays.asList(
                TrainingResponseDTO.builder().id(1L).name("Training 1").build(),
                TrainingResponseDTO.builder().id(2L).name("Training 2").build());
    }

    private UserRequestDTO createTestUserRequestDTO() {
        return UserRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
    }

    private UserResponseDTO createTestUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .isActive(user.getIsActive())
                .build();
    }

    private TraineeRequestDTO createTestTraineeRequestDTO() {
        return TraineeRequestDTO.builder()
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .address("Test Address")
                .user(createTestUserRequestDTO())
                .trainerIds(Arrays.asList(1L, 2L))
                .trainingIds(Arrays.asList(1L, 2L))
                .build();
    }

    private List<Trainer> createTestTrainers() {
        return Arrays.asList(
                Trainer.builder().id(1L).specialization(createTestTrainingType()).user(createTestUser()).trainings(createTestTrainings()).build(),
                Trainer.builder().id(2L).specialization(createTestTrainingType()).user(createTestUser()).trainings(createTestTrainings()).build()
        );
    }

    private User createTestUser() {
        return User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Username")
                .username("Test.Username")
                .password("password")
                .isActive(true)
                .build();
    }

    private List<Training> createTestTrainings() {
        return Arrays.asList(
                Training.builder().id(1L).name("Training 1").trainee(createTestTrainee()).trainer(createTestTrainer()).build(),
                Training.builder().id(2L).name("Training 2").trainee(createTestTrainee()).trainer(createTestTrainer()).build()
        );
    }

    private TrainingType createTestTrainingType() {
        return TrainingType.builder()
                .id(1L)
                .name("Test Training Type")
                .build();
    }

    private List<Trainee> createTestTrainees() {
        return Arrays.asList(
                Trainee.builder().id(1L).address("Test Address 1").dateOfBirth(LocalDate.of(1990, 1, 1)).user(createTestUser()).trainings(createTestTrainings()).trainers(createTestTrainers()).build(),
                Trainee.builder().id(2L).address("Test Address 2").dateOfBirth(LocalDate.of(1995, 5, 5)).user(createTestUser()).trainings(createTestTrainings()).trainers(createTestTrainers()).build()
        );
    }
}


*/
