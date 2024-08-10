package org.pronsky.landmark_service.service.impl;

import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.BaseTest;
import org.pronsky.landmark_service.data.repository.SettlementRepository;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SettlementServiceImplTest extends BaseTest {

    @MockBean
    private EntityDtoMapper mapper;

    @MockBean
    private SettlementRepository repository;

    @Autowired
    private SettlementServiceImpl settlementService;

    @Test
    void testCreateSettlement() {

        when(mapper.toEntity(settlementDtoForCreate)).thenReturn(settlementForCreate);
        when(repository.save(settlementForCreate)).thenReturn(createdSettlement);
        when(mapper.toDto(createdSettlement)).thenReturn(createdSettlementDto);

        SettlementDto createdSettlement = settlementService.create(settlementDtoForCreate);

        verify(repository).save(settlementForCreate);
        assertEquals(createdSettlementDto, createdSettlement);
    }

    @Test
    void testUpdateSettlement_Success() {

        when(repository.findById(settlementDto.getId())).thenReturn(Optional.of(settlement));
        when(mapper.toEntity(settlementDto)).thenReturn(settlement);
        when(repository.save(settlement)).thenReturn(settlement);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);

        SettlementDto updatedSettlement = settlementService.update(settlementDto);

        verify(repository).findById(settlementDto.getId());
        verify(repository).save(settlement);
        assertEquals(settlementDto, updatedSettlement);
    }

    @Test
    void testUpdateSettlement_Forbidden() {

        when(repository.findById(incorrectSettlementDtoForUpdate.getId())).thenReturn(Optional.of(settlement));
        when(mapper.toEntity(settlementDto)).thenReturn(settlement);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);

        Throwable thrown = catchThrowable(() -> settlementService.update(incorrectSettlementDtoForUpdate));

        assertThat(thrown)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Forbidden");
    }

    @Test
    void testUpdateSettlement_NotFound() {

        when(repository.findById(settlementDto.getId())).thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> settlementService.update(settlementDto));

        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }
}
