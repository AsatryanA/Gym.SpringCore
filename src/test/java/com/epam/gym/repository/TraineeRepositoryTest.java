package com.epam.gym.repository;

import com.epam.gym.model.Trainee;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.TrainingType;
import com.epam.gym.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TraineeRepositoryTest {

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    TestEntityManager entityManager;

    private Trainee newTrainee;

    @BeforeEach
    void setUp() {
        newTrainee = createTrainee();
    }

    @Test
    void givenNewTrainee_whenSave_thenSuccess() {
        var savedTrainee = traineeRepository.save(newTrainee);
        Assertions.assertThat(entityManager.find(Trainee.class, savedTrainee.getId()))
                .isEqualTo(newTrainee);
    }

    @Test
    void givenTraineeId_whenGetById_thenSuccess() {
        var savedTrainee = entityManager.persist(newTrainee);
        var foundTrainee = traineeRepository.findById(savedTrainee.getId());
        Assertions.assertThat(foundTrainee).isPresent().contains(savedTrainee);
    }

    @Test
    void givenTrainee_whenUpdate_thenSuccess() {
        var savedTrainee = entityManager.persist(newTrainee);
        savedTrainee.setAddress("New address");
        var updatedTrainee = traineeRepository.save(savedTrainee);
        Assertions.assertThat(updatedTrainee).isEqualTo(savedTrainee);
    }

    @Test
    void givenTraineeId_whenDelete_thenSuccess() {
        var savedTrainee = entityManager.persist(newTrainee);
        traineeRepository.deleteById(savedTrainee.getId());
        Assertions.assertThat(entityManager.find(Trainee.class, savedTrainee.getId()))
                .isNull();
    }

    @Test
    void givenTrainers_whenUpdateTrainers_thenSuccess() {
        var savedTrainee = entityManager.persist(newTrainee);
        ArrayList<Trainer> trainers = new ArrayList<>();
        trainers.add(entityManager.persist(createTrainer()));
        trainers.add(entityManager.persist(createTrainer()));
        trainers.add(entityManager.persist(createTrainer()));
        savedTrainee.setTrainers(trainers);
        var updatedTrainee = traineeRepository.save(savedTrainee);
        Assertions.assertThat(updatedTrainee.getTrainers()).hasSize(3);
        Assertions.assertThat(entityManager.find(Trainee.class, updatedTrainee.getId()))
                .isEqualTo(savedTrainee);
    }


    @Test
    void givenTraineeId_whenGetTrainings_thenSuccess() {
        var savedTrainee = entityManager.persist(newTrainee);
        Assertions.assertThat(savedTrainee.getTrainings()).isNull();
    }

    @Test
    void givenToggleActive_whenToggleActive_thenSuccess() {
        var savedTrainee = entityManager.persist(newTrainee);
        savedTrainee.getUser().setIsActive(false);
        var updatedTrainee = traineeRepository.save(savedTrainee);
        Assertions.assertThat(updatedTrainee.getUser().getIsActive()).isFalse();
    }

    private Trainee createTrainee() {
        return Trainee.builder()
                .user(User.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .isActive(true)
                        .password("asjd34*(^3")
                        .username("John.Doe")
                        .build())
                .address("Some address")
                .dateOfBirth(LocalDate.now())
                .build();
    }

    private Trainer createTrainer() {
        return Trainer.builder()
                .user(User.builder()
                        .firstName("trainer")
                        .lastName("trainer")
                        .isActive(true)
                        .password("asjd34*(^3")
                        .username("trainer.1")
                        .build())
                .specialization(TrainingType.builder()
                        .id(1L)
                        .name("Cardio")
                        .build())
                .build();
    }
}
