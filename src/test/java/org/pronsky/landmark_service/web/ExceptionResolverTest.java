package org.pronsky.landmark_service.web;

import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.BaseTest;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExceptionResolverTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LandmarkService landmarkService;

    @Test
    void testHandleBadRequest() throws Exception {
        when(landmarkService.getAllByTypeSorted(anyString(), anyString())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/api/landmarks")
                        .param("sortingParam", " ")
                        .param("type", " "))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testHandleForbidden() throws Exception {
        when(landmarkService.getAllBySettlement(anyString()))
                .thenThrow(new UnsupportedOperationException("Forbidden operation"));

        mockMvc.perform(get("/api/landmarks/by_settlement")
                        .param("settlement", "TestCity"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testHandleInternalServerError() throws Exception {
        when(landmarkService.create(new LandmarkTrimmedDto())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/testInternalServerError"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testHandleNotFound() throws Exception {
        when(landmarkService.getAllBySettlement(anyString()))
                .thenThrow(new NullPointerException());

        mockMvc.perform(get("/api/landmarks/by_settlement")
                        .param("settlement", "TestCity"))
                .andExpect(status().isNotFound());
    }
}
