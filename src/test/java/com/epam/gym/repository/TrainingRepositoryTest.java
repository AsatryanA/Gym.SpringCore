package com.epam.gym.repository;

import com.epam.gym.model.Trainee;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.Training;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrainingRepositoryTest {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TestEntityManager entityManager;

    private Training newTraining;

    @BeforeEach
    void setUp() {
        newTraining = createTraining();
    }

    @Test
    void givenNewTraining_whenSave_thenSuccess() {
        var savedTraining = trainingRepository.save(newTraining);
        Assertions.assertThat(entityManager.find(Training.class, savedTraining.getId()))
                .isEqualTo(newTraining);
    }

    private Training createTraining() {
        return Training.builder()
                .name("Test Training")
                .trainer(Trainer.builder().user(User.builder()
                        .lastName("last name")
                        .firstName("first name")
                        .password("password")
                        .username("username")
                        .build()).build())
                .trainee(Trainee.builder().address("address").build())
                .date(LocalDate.now())
                .duration(500)
                .trainingType(TrainingType.builder().name("Cardio").build())
                .build();
    }
}
