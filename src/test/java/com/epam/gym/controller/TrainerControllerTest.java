package com.epam.gym.controller;

import com.epam.gym.model.TrainingType;
import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TrainerRequestDTO;
import com.epam.gym.model.dto.request.TrainerUpdateDTO;
import com.epam.gym.service.TrainerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TrainerControllerTest {

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();
    }

    @Test
    void shouldCreateTrainer() throws Exception {
        TrainerRequestDTO requestDTO = TrainerRequestDTO.builder()
                .lastName("Doe")
                .firstName("John")
                .specialization(TrainingType.builder().id(1L).build())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/trainers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetTrainerById() throws Exception {
        Long trainerId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trainers/{id}", trainerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTrainer() throws Exception {
        TrainerUpdateDTO updateDTO = TrainerUpdateDTO.builder()
                .specialization(TrainingType.builder()
                        .id(2L)
                        .build())
                .firstName("Joe")
                .lastName("Last")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/trainers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetTrainerTrainings() throws Exception {
        Long trainerId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trainers/{id}/trainings", trainerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldToggleTrainerActiveStatus() throws Exception {
        var toggleActiveDTO = ToggleActiveDTO.builder()
                .id(1L)
                .isActive(true)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/trainers/toggle-active")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(toggleActiveDTO)))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
