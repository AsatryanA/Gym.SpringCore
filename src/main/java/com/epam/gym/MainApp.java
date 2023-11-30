package com.epam.gym;

import com.epam.gym.service.GymFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class MainApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var gymFacade = context.getBean("gymFacade", GymFacade.class);
        log.info(gymFacade.getTraineeService().getById(4L).toString());
    }
}
