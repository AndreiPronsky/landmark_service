package org.pronsky.landmark_service.web.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.service.SettlementService;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SettlementControllerImpl.class)
class SettlementControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SettlementService service;

    private SettlementDto settlementDto;

    @BeforeEach
    void setUp() {
        settlementDto = new SettlementDto();
    }

    @Test
    void testDoPost() throws Exception {
        when(service.create(settlementDto)).thenReturn(settlementDto);

        mockMvc.perform(post("/api/settlements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(settlementDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDoPatch() throws Exception {
        when(service.update(settlementDto)).thenReturn(settlementDto);

        mockMvc.perform(patch("/api/settlements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(settlementDto)))
                .andExpect(status().isAccepted());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
