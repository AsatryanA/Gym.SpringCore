package com.epam.gym.repository;

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

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrainerRepositoryTest {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    TestEntityManager entityManager;

    private Trainer newTrainer;

    @BeforeEach
    void setUp() {
        newTrainer = createTrainer();
    }

    @Test
    void givenNewTrainer_whenSave_thenSuccess() {
        var savedTrainer = trainerRepository.save(newTrainer);
        Assertions.assertThat(entityManager.find(Trainer.class, savedTrainer.getId())).isEqualTo(newTrainer);
    }

    @Test
    void givenTrainerId_whenGetById_thenSuccess() {
        var savedTrainer = entityManager.persist(newTrainer);
        var foundTrainer = trainerRepository.findById(savedTrainer.getId());
        Assertions.assertThat(foundTrainer).isPresent().contains(savedTrainer);
    }

    @Test
    void givenTrainer_whenUpdate_thenSuccess() {
        var savedTrainer = entityManager.persist(newTrainer);
        savedTrainer.setSpecialization(TrainingType.builder().id(2L).name("Strength").build());
        var updatedTrainer = trainerRepository.save(savedTrainer);
        Assertions.assertThat(updatedTrainer).isEqualTo(savedTrainer);
    }

    @Test
    void givenTrainerIds_whenGetByIds_thenSuccess() {
        var trainingType = entityManager.persist(TrainingType.builder()
                .name("Cardio")
                .build());
        newTrainer.setSpecialization(trainingType);
        var savedTrainer = entityManager.persist(newTrainer);
        var foundTrainers = trainerRepository.findAllById(List.of(savedTrainer.getId()));
        Assertions.assertThat(foundTrainers).hasSize(1);
    }

    @Test
    void givenTrainerId_whenGetTrainings_thenSuccess() {
        var savedTrainer = entityManager.persist(newTrainer);
        Assertions.assertThat(savedTrainer.getTrainings()).isNull();
    }

    @Test
    void givenToggleActive_whenToggleActive_thenSuccess() {
        var savedTrainer = entityManager.persist(newTrainer);
        savedTrainer.getUser().setIsActive(false);
        var updatedTrainer = trainerRepository.save(savedTrainer);
        Assertions.assertThat(updatedTrainer.getUser().getIsActive()).isFalse();
    }

    private Trainer createTrainer() {
        return Trainer.builder()
                .user(User.builder()
                        .firstName("trainer")
                        .lastName("trainer").isActive(true)
                        .password("asjd34*(^3")
                        .username("trainer.1")
                        .build())
                .specialization(TrainingType
                        .builder()
                        .name("Cardio")
                        .build())
                .build();
    }
}
