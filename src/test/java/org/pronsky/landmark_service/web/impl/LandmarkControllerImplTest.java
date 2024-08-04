package org.pronsky.landmark_service.web.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LandmarkControllerImpl.class)
class LandmarkControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LandmarkService service;

    private LandmarkFullDto landmarkDto;
    private LandmarkTrimmedDto landmarkTrimmedDto;

    @BeforeEach
    void setUp() {
        landmarkDto = new LandmarkFullDto();
        landmarkTrimmedDto = new LandmarkTrimmedDto();
    }

    @Test
    void testGetAllBySettlement() throws Exception {
        when(service.getAllBySettlement("anySettlement")).thenReturn(List.of(landmarkDto));

        mockMvc.perform(get("/api/landmarks/by_settlement")
                        .param("settlement", "anySettlement"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAllByTypeSorted("anyType", "anySortingParam"))
                .thenReturn(List.of(landmarkDto));

        mockMvc.perform(get("/api/landmarks")
                        .param("sortingParam", "anySortingParam")
                        .param("type", "anyType"))
                .andExpect(status().isOk());
    }

    @Test
    void testDoPost() throws Exception {
        when(service.create(landmarkTrimmedDto)).thenReturn(landmarkDto);

        mockMvc.perform(post("/api/landmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(landmarkTrimmedDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDoPatch() throws Exception {
        when(service.update(landmarkTrimmedDto)).thenReturn(landmarkDto);

        mockMvc.perform(patch("/api/landmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(landmarkTrimmedDto)))
                .andExpect(status().isAccepted());
    }

    @Test
    void testDoDelete() throws Exception {
        mockMvc.perform(delete("/api/landmarks")
                        .param("id", String.valueOf(1L)))
                .andExpect(status().isNoContent());

        verify(service).delete(1L);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
