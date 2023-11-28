package com.epam.gym;

import com.epam.gym.entity.TrainingType;
import com.epam.gym.entity.dto.request.TrainerUpdateDTO;
import com.epam.gym.entity.dto.request.UserUpdateDTO;
import com.epam.gym.service.GymFacade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var gymFacade = context.getBean(GymFacade.class);
       /* var trainerRequestDTO = TrainerRequestDTO
                .builder()
                .user(UserRequestDTO
                        .builder()
                        .firstName("Arsen")
                        .lastName("Asatryan")
                        .build())
                .specialization(TrainingType.builder()
                        .id(1L)
                        .name("Boxing").build())
                .traineeIds(new ArrayList<>(Arrays.asList(2L, 3L, 4L)))
                .trainingIds(new ArrayList<>(Arrays.asList(1L, 2L, 3L)))
                .build();
*/

/*        TraineeRequestDTO build = TraineeRequestDTO.builder()
                .dateOfBirth(LocalDate.now())
                .address("Hasce")
                .trainerIds(new ArrayList<>(Arrays.asList(5L, 6L, 7L)))
                .trainingIds(new ArrayList<>(Arrays.asList(1L, 2L, 3L)))
                .user(UserRequestDTO
                        .builder()
                        .firstName("Ars")
                        .lastName("Asatryan")
                        .build())
                .build();*/
        //  var john = gymFacade.getTraineeService().getByUsername("john.doe");
        // var ars = gymFacade.getTrainerService().getByUsername("Arsen.Asatryan2");

        // gymFacade.getTrainerService().changePassword(ChangePasswordDTO.builder().newPassword("123456").oldPassword("123").id(8L).build());

        gymFacade.getTrainerService().update(TrainerUpdateDTO
                .builder()
                .id(5L).user(UserUpdateDTO
                        .builder()
                        .id(9L)
                        .firstName("Arsen").lastName("Asatryan")
                        .build())
                .specialization(TrainingType.builder().id(7L).name("Boxing").build())

                .build());

    }

}
