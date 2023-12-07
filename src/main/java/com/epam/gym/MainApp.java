package com.epam.gym;

import com.epam.gym.service.GymFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class MainApp {
    // todo point 6, 9, 10
    // todo messages and exception
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var gymFacade = context.getBean("gymFacade", GymFacade.class);
        gymFacade.getTraineeService().delete(65L);
    }

}
