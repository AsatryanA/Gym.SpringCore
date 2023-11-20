package com.epam.gym;

import com.epam.gym.dao.impl.TraineeDAOImpl;
import com.epam.gym.dao.impl.TrainerDAOImpl;
import com.epam.gym.dao.impl.TrainingDAOImpl;
import com.epam.gym.dao.impl.UserDAOImpl;
import com.epam.gym.service.UserService;
import com.epam.gym.util.InMemoryStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfig {

    @Bean
    UserDAOImpl userDAO() {
        return new UserDAOImpl();
    }

    @Bean
    TraineeDAOImpl traineeDAO() {
        return new TraineeDAOImpl();
    }

    @Bean
    TrainerDAOImpl trainerDAO() {
        return new TrainerDAOImpl();
    }

    @Bean
    TrainingDAOImpl trainingDAO() {
        return new TrainingDAOImpl();
    }

    @Bean
    UserService userService() {
        return new UserService(userDAO());
    }

    @Bean
    InMemoryStorage inMemoryStorage() {
        return new InMemoryStorage();
    }
}
