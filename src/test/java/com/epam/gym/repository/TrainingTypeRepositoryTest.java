package com.epam.gym.repository;

import com.epam.gym.model.TrainingType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrainingTypeRepositoryTest {

    @Autowired
    TrainingTypeRepository trainingTypeRepository;

    @Autowired
    TestEntityManager entityManager;

    private TrainingType newTrainingType;

    void setUp() {
        newTrainingType = createTrainingType();
    }

    @Test
    void givenTrainingTypeId_whenGetById_thenSuccess() {
        var savedTrainingType = entityManager.persist(newTrainingType);
        var foundTrainingType = trainingTypeRepository.findById(savedTrainingType.getId());
        Assertions.assertThat(foundTrainingType).isPresent().contains(savedTrainingType);
    }

    @Test
    void givenPageable_whenGetAll_thenSuccess() {
        var savedTrainingType = entityManager.persist(newTrainingType);
        var foundTrainingType = trainingTypeRepository.findAll(Pageable
                .ofSize(1).withPage(0));
        Assertions.assertThat(foundTrainingType).hasSize(1);
    }

    private TrainingType createTrainingType() {
        return TrainingType.builder()
                .name("Test Cardio")
                .build();
    }
}
