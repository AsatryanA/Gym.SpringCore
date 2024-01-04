package com.epam.gym.repository;

import com.epam.gym.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;
    private User newUser;

    @BeforeEach
    void setUp() {
        newUser = createUser();
    }

    @Test
    void givenNewUser_whenSave_thenSuccess() {
        var savedUser = userRepository.save(newUser);
        Assertions.assertThat(entityManager.find(User.class, savedUser.getId()))
                .isEqualTo(newUser);
    }

    @Test
    void givenUserId_whenGetById_thenSuccess() {
        var savedUser = entityManager.persist(newUser);
        var foundUser = userRepository.findById(savedUser.getId());
        Assertions.assertThat(foundUser).isPresent().contains(savedUser);
    }

/*    @Test
    void givenUsernameAndPassword_whenFindByUsernameAndPassword_thenSuccess() {
        var savedUser = entityManager.persist(newUser);
        var foundUser = userRepository.findByUsername(savedUser.getUsername(), savedUser.getPassword());
        Assertions.assertThat(foundUser).isPresent().contains(savedUser);
    }*/

    @Test
    void givenUsername_whenExistsByUsername_thenSuccess() {
        var savedUser = entityManager.persist(newUser);
        var foundUser = userRepository.existsByUsername(savedUser.getUsername());
        Assertions.assertThat(foundUser).isTrue();
    }


    private User createUser() {
        return User.builder()
                .lastName("John")
                .firstName("Doe")
                .password("Wdj&^%856F")
                .username("John.Doe")
                .isActive(true)
                .build();
    }
}
