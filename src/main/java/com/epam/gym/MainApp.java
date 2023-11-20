package com.epam.gym;

import com.epam.gym.entity.dto.TraineeDTO;
import com.epam.gym.service.TraineeService;
import com.epam.gym.util.InMemoryStorage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class MainApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var bean = context.getBean(InMemoryStorage.class);
        System.out.println("TrainerStorage:");
        bean.getTrainerStorage().values().forEach(System.out::println);
        System.out.println("TraineeStorage:");
        bean.getTraineeStorage().values().forEach(System.out::println);
        System.out.println("TrainingStorage:");
        bean.getTrainingStorage().values().forEach(System.out::println);
        System.out.println("TrainingTypeStorage:");
        bean.getTrainingTypeStorage().values().forEach(System.out::println);
        System.out.println("UserStorage:");
        bean.getUserStorage().values().forEach(System.out::println);

        var traineeDTO = new TraineeDTO(new Date(), "address");
        traineeDTO.setFirstName("firstName");
        traineeDTO.setLastName("lastName");
        TraineeDTO traineeDTO2 = new TraineeDTO(new Date(), "address");
        traineeDTO2.setFirstName("firstName");
        traineeDTO2.setLastName("lastName");
        var bean1 = context.getBean(TraineeService.class);
        bean1.createTrainee(traineeDTO);

        bean.getTrainerStorage().values().forEach(System.out::println);
        System.out.println("TraineeStorage:");

        bean.getUserStorage().values().forEach(System.out::println);
        bean1.createTrainee(traineeDTO2);
    }
}
