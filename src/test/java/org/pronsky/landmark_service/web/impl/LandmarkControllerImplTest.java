package org.pronsky.landmark_service.web.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.BaseTest;
import org.pronsky.landmark_service.service.LandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LandmarkControllerImplTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LandmarkService service;

    @Test
    void testGetAllBySettlement() throws Exception {
        when(service.getAllBySettlement("anySettlement")).thenReturn(landmarkDtoList);

        mockMvc.perform(get("/api/landmarks/by_settlement")
                        .param("settlement", "anySettlement"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAllByTypeSorted(LANDMARK_TYPE, NAME_SORTING_PARAM))
                .thenReturn(landmarkDtoList);

        mockMvc.perform(get("/api/landmarks")
                        .param("sortingParam", NAME_SORTING_PARAM)
                        .param("type", LANDMARK_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    void testDoPost() throws Exception {
        when(service.create(correctTrimmedForUpdate)).thenReturn(landmarkDto);

        mockMvc.perform(post("/api/landmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(correctTrimmedForUpdate)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDoPatch() throws Exception {
        when(service.update(correctTrimmedForUpdate)).thenReturn(correctFullForUpdate);

        mockMvc.perform(patch("/api/landmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(correctTrimmedForUpdate)))
                .andExpect(status().isAccepted());
    }

    @Test
    void testDoDelete() throws Exception {
        mockMvc.perform(delete("/api/landmarks")
                        .param("id", String.valueOf(1L)))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
