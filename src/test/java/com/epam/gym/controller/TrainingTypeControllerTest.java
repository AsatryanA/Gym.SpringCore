package com.epam.gym.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TrainingTypeControllerTest {

    @InjectMocks
    private TrainingTypeController trainingTypeController;

    @Mock
    private TrainingTypeServiceTest trainingTypeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainingTypeController).build();
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/v1/training-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}