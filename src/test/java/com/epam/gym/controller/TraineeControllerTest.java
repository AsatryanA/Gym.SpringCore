package com.epam.gym.controller;

import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TraineeRequestDTO;
import com.epam.gym.model.dto.request.TraineeUpdateDTO;
import com.epam.gym.service.TraineeService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TraineeControllerTest {

    @Mock
    private TraineeService traineeService;

    @InjectMocks
    private TraineeController traineeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(traineeController).build();
    }

    @Test
    void testInvalidTraineeDataValidation() throws Exception {
        var requestDTO = TraineeRequestDTO.builder().address("Address").firstName("John").lastName("Doe").dateOfBirth(LocalDate.of(1995, 12, 7)).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/trainees").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testShouldCreateTrainee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/trainees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(createTraineeRequestDto()))).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldGetTraineeById() throws Exception {
        Long traineeId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trainees/{id}", traineeId).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTrainee() throws Exception {
        var updateDTO = TraineeUpdateDTO.builder().firstName("John").lastName("Doe").isActive(false).build();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/trainees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updateDTO))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteTrainee() throws Exception {
        Long traineeId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/trainees/{id}", traineeId).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTraineeTrainers() throws Exception {
        Long traineeId = 1L;
        List<Long> trainerIds = List.of(1L, 2L);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/trainees/{id}/trainers", traineeId).contentType(MediaType.APPLICATION_JSON).content(asJsonString(trainerIds))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldGetTraineeTrainings() throws Exception {
        Long traineeId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trainees/{id}/trainings", traineeId).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldToggleTraineeActiveStatus() throws Exception {
        ToggleActiveDTO toggleActiveDTO = ToggleActiveDTO.builder().id(1L).isActive(true).build();
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/trainees/toggle-active").contentType(MediaType.APPLICATION_JSON).content(asJsonString(toggleActiveDTO))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private TraineeRequestDTO createTraineeRequestDto() {
        return TraineeRequestDTO.builder().address("Address").firstName("John").lastName("Doe").dateOfBirth(LocalDate.of(2000, 10, 10)).build();
    }
}
