package com.epam.gym.service;

import com.epam.gym.SpringTestConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = SpringTestConfig.class)
class TraineeServiceTest {
/*

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private UserService userService;

    @InjectMocks
    private TraineeService traineeService;

    @Test
    void createTrainee_ShouldCreateTrainee() {
        // Arrange
        var traineeDTO = new TraineeDTO(new Date(), "some address");
        traineeDTO.setFirstName("John");
        traineeDTO.setLastName("Doe");

        var user = new User();
        user.setId(11L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername(user.getFirstName() + "." + user.getLastName());
        user.setPassword(PasswordGenerator.generateRandomPassword());

        var trainee = new Trainee(traineeDTO.getDateOfBirth(), traineeDTO.getAddress(), user.getId());

        when(userService.createUser(any(TraineeDTO.class))).thenReturn(user);
        when(traineeDAO.createTrainee(any(Trainee.class))).thenReturn(trainee);
when()
        // Act
        Trainee createdTrainee = traineeService.createTrainee(traineeDTO);

        // Assert
        assertEquals(user.getId(), createdTrainee.getUserId());
        verify(userService, Mockito.times(1)).createUser(traineeDTO);
        verify(traineeDAO, Mockito.times(1)).createTrainee(any(Trainee.class));
    }
*/

   /* @Test
    void getTraineeById_ShouldReturnTrainee() {
        // Arrange
        long traineeId = 1L;
        Trainee expectedTrainee = new Trainee(traineeId, "John Doe", 1L);

        when(traineeDAO.getTraineeById(traineeId)).thenReturn(expectedTrainee);

        // Act
        Trainee retrievedTrainee = traineeService.getTraineeById(traineeId);

        // Assert
        assertEquals(expectedTrainee, retrievedTrainee);
        verify(traineeDAO, Mockito.times(1)).getTraineeById(traineeId);
    }

    @Test
    void deleteTrainee_ShouldDeleteTrainee() {
        // Arrange
        long traineeId = 1L;

        // Act
        traineeService.deleteTrainee(traineeId);

        // Assert
        verify(traineeDAO, Mockito.times(1)).deleteTrainee(traineeId);
    }

    @Test
    void updateTrainee_ShouldUpdateTrainee() {
        // Arrange
        TraineeDTO traineeDTO = new TraineeDTO("John Doe", "john.doe@example.com");
        User user = new User(1L, "John Doe", "john.doe@example.com");
        Trainee trainee = new Trainee(1L, "John Doe", user.getId());

        when(userService.createUser(any(TraineeDTO.class))).thenReturn(user);
        when(traineeDAO.updateTrainee(any(Trainee.class))).thenReturn(trainee);

        // Act
        Trainee updatedTrainee = traineeService.updateTrainee(traineeDTO);

        // Assert
        assertEquals(user.getId(), updatedTrainee.getUserId());
        verify(userService, Mockito.times(1)).createUser(traineeDTO);
        verify(traineeDAO, Mockito.times(1)).updateTrainee(any(Trainee.class));
    }*/
}