package org.pronsky.landmark_service.service.impl;

import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.BaseTest;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.entities.Service;
import org.pronsky.landmark_service.data.repository.LandmarkRepository;
import org.pronsky.landmark_service.data.repository.ServiceRepository;
import org.pronsky.landmark_service.data.repository.SettlementRepository;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LandmarkServiceImplTest extends BaseTest {

    @Autowired
    private LandmarkService landmarkService;

    @MockBean
    private LandmarkRepository landmarkRepository;

    @MockBean
    private SettlementRepository settlementRepository;

    @MockBean
    private ServiceRepository serviceRepository;

    @MockBean
    private EntityDtoMapper mapper;

    @Test
    void testGetAllByTypeSorted_ByName() {
        when(landmarkRepository.findAllByTypeOrderByName(Landmark.LandmarkType.valueOf(LANDMARK_TYPE)))
                .thenReturn(landmarksSortedByName);
        when(mapper.toDto(landmark)).thenReturn(landmarkDto);
        when(mapper.toDto(anotherLandmark)).thenReturn(anotherLandmarkDto);

        List<LandmarkFullDto> result = landmarkService.getAllByTypeSorted(LANDMARK_TYPE, NAME_SORTING_PARAM);

        assertEquals(landmarksSortedByName.size(), result.size());
        assertTrue(result.get(0).getName().compareTo(result.get(1).getName()) < 0);
    }

    @Test
    void testGetAllByTypeSorted_BySettlementName() {
        when(landmarkRepository.findAllByTypeOrderBySettlementName(Landmark.LandmarkType.valueOf(LANDMARK_TYPE)))
                .thenReturn(landmarksSortedBySettlement);
        when(mapper.toDto(landmark)).thenReturn(landmarkDto);
        when(mapper.toDto(anotherLandmark)).thenReturn(anotherLandmarkDto);

        List<LandmarkFullDto> result = landmarkService.getAllByTypeSorted(LANDMARK_TYPE, SETTLEMENT_SORTING_PARAM);

        assertEquals(result.size(), landmarksSortedBySettlement.size());
        assertTrue((result.get(0).getSettlement().getName()
                .compareTo(result.get(1).getSettlement().getName())) < 0);
    }

    @Test
    void testGetAllByTypeSorted_ByCreationYear() {
        when(landmarkRepository.findAllByTypeOrderByCreationYear(Landmark.LandmarkType.valueOf(LANDMARK_TYPE)))
                .thenReturn(landmarksSortedByYear);
        when(mapper.toDto(landmark)).thenReturn(landmarkDto);
        when(mapper.toDto(anotherLandmark)).thenReturn(anotherLandmarkDto);

        List<LandmarkFullDto> result = landmarkService.getAllByTypeSorted(LANDMARK_TYPE, CREATION_YEAR_SORTING_PARAM);

        assertEquals(result.size(), landmarksSortedByYear.size());
        assertTrue(result.get(0).getCreationYear() < result.get(1).getCreationYear());
    }

    @Test
    void getAllBySettlement() {
        when(landmarkRepository.findAllBySettlementName(settlementDto.getName()))
                .thenReturn(sameSettlementLandmarkList);
        when(mapper.toDto(landmark)).thenReturn(landmarkDto);
        when(mapper.toDto(landmark1)).thenReturn(landmarkDto1);

        List<LandmarkFullDto> result = landmarkService.getAllBySettlement(settlementDto.getName());

        assertEquals(result.size(), sameSettlementLandmarkDtoList.size());
        assertEquals(sameSettlementLandmarkDtoList.get(0).getSettlement().getName()
                , result.get(0).getSettlement().getName());
        assertEquals(sameSettlementLandmarkDtoList.get(1).getSettlement().getName()
                , result.get(1).getSettlement().getName());
        assertEquals(result.get(0).getSettlement().getName(), result.get(1).getSettlement().getName());
    }

    @Test
    void testUpdate_FullUpdate_ThrowsException() {
        when(landmarkRepository.findById(incorrectDtoForUpdate.getId()))
                .thenReturn(Optional.ofNullable(landmarkForUpdate));
        when(settlementRepository.findByName(incorrectDtoForUpdate.getSettlementName()))
                .thenReturn(settlement);
        when(mapper.toDto(landmarkForUpdate)).thenReturn(landmarkDto);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);
        when(mapper.toFullLandmarkDto(incorrectDtoForUpdate)).thenReturn(incorrectFullDtoForUpdate);

        Throwable thrown = catchThrowable(() -> landmarkService.update(incorrectDtoForUpdate));

        assertThat(thrown)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Forbidden");
    }

    @Test
    void testUpdate_DescriptionUpdate() {
        when(landmarkRepository.findById(landmarkForUpdate.getId())).thenReturn(Optional.ofNullable(landmarkForUpdate));
        when(settlementRepository.findByName(correctTrimmedForUpdate.getSettlementName())).thenReturn(settlement);
        when(serviceRepository.save(any(Service.class))).thenReturn(service);
        when(landmarkRepository.save(any(Landmark.class))).thenReturn(landmarkForUpdate);

        when(mapper.toDto(landmarkForUpdate)).thenReturn(fullDtoForUpdate);
        when(mapper.toDto(service)).thenReturn(serviceDto);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);
        when(mapper.toEntity(fullDtoForUpdate)).thenReturn(landmarkForUpdate);
        when(mapper.toEntity(serviceDto)).thenReturn(service);
        when(mapper.toEntity(settlementDto)).thenReturn(settlement);
        when(mapper.toFullLandmarkDto(correctTrimmedForUpdate)).thenReturn(fullDtoForUpdate);

        LandmarkFullDto updated = landmarkService.update(correctTrimmedForUpdate);

        assertEquals(updated.getId(), correctTrimmedForUpdate.getId());
        verify(landmarkRepository, times(1)).save(any(Landmark.class));
    }

    @Test
    void testCreate() {
        when(settlementRepository.findByName(trimmedDtoForCreate.getSettlementName())).thenReturn(settlement);
        when(landmarkRepository.save(landmarkForCreate)).thenReturn(createdLandmark);
        when(serviceRepository.save(service)).thenReturn(service);
        when(mapper.toDto(createdLandmark)).thenReturn(landmarkDto);
        when(mapper.toEntity(any(LandmarkFullDto.class))).thenReturn(landmarkForCreate);
        when(mapper.toFullLandmarkDto(trimmedDtoForCreate)).thenReturn(fullDtoForCreate);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);
        when(mapper.toDto(service)).thenReturn(serviceDto);
        when(mapper.toDto(any(Landmark.class))).thenReturn(landmarkDto);

        LandmarkFullDto createdDto = landmarkService.create(trimmedDtoForCreate);

        assertNotNull(createdDto.getId());
        verify(landmarkRepository, times(1)).save(any(Landmark.class));
    }

    @Test
    void testDelete() {

        landmarkService.delete(landmarkDto.getId());

        verify(landmarkRepository, times(1)).deleteById(any(Long.class));
    }
}
