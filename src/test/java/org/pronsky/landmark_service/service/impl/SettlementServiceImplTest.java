package org.pronsky.landmark_service.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.entities.Service;
import org.pronsky.landmark_service.data.entities.Settlement;
import org.pronsky.landmark_service.data.repository.SettlementRepository;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.ServiceDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SettlementServiceImplTest {

    @Mock
    private EntityDtoMapper mapper;

    @Mock
    private SettlementRepository repository;

    @InjectMocks
    private SettlementServiceImpl settlementService;

    private static SettlementDto settlementDto;
    private static Settlement settlement;
    private static Service service;
    private static ServiceDto serviceDto;
    private static List<Service> services;
    private static List<ServiceDto> serviceDtoList;
    private static Landmark landmark;
    private static Landmark anotherLandmark;
    private static List<Landmark> landmarks;
    private static LandmarkFullDto landmarkDto;
    private static LandmarkFullDto anotherLandmarkDto;
    private static List<LandmarkFullDto> landmarkDtoList;
    private static SettlementDto dtoForCreate;
    private static Settlement entityForCreate;
    private static SettlementDto incorrectDtoForUpdate;
    private static Settlement createdEntity;
    private static SettlementDto createdDto;

    @BeforeEach
    void setUp() {

        service = new Service();
        service.setId(1L);
        service.setName("Test service");
        service.setDescription("Test description");
        service.setLandmarkId(1L);

        services = new ArrayList<>();
        services.add(service);

        serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setName("Test service");
        serviceDto.setDescription("Test description");
        serviceDto.setLandmarkId(1L);

        serviceDtoList = new ArrayList<>();
        serviceDtoList.add(serviceDto);

        settlement = new Settlement();
        settlement.setId(1L);
        settlement.setName("Test settlement");
        settlement.setPopulation(2000000L);
        settlement.setHasSubway(true);

        landmark = new Landmark();
        landmark.setId(1L);
        landmark.setName("Test landmark A");
        landmark.setType(Landmark.LandmarkType.HISTORICAL);
        landmark.setCreationYear((short) 1999);
        landmark.setDescription("TestDescription");
        landmark.setServices(services);
        landmark.setSettlement(settlement);

        anotherLandmark = new Landmark();
        anotherLandmark.setId(2L);
        anotherLandmark.setName("Test landmark B");
        anotherLandmark.setType(Landmark.LandmarkType.HISTORICAL);
        anotherLandmark.setCreationYear((short) 2000);
        anotherLandmark.setDescription("AnotherTestDescription");
        anotherLandmark.setServices(services);
        anotherLandmark.setSettlement(settlement);

        landmarks = new ArrayList<>();
        landmarks.add(landmark);
        landmarks.add(anotherLandmark);

        settlement.setLandmarks(landmarks);

        landmarkDto = new LandmarkFullDto();
        landmarkDto.setId(1L);
        landmarkDto.setName("Test landmark A");
        landmarkDto.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        landmarkDto.setCreationYear((short) 1999);
        landmarkDto.setDescription("TestDescription");
        landmarkDto.setServices(serviceDtoList);
        landmarkDto.setSettlement(settlementDto);

        anotherLandmarkDto = new LandmarkFullDto();
        anotherLandmarkDto.setId(2L);
        anotherLandmarkDto.setName("Test landmark B");
        anotherLandmarkDto.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        anotherLandmarkDto.setCreationYear((short) 2000);
        anotherLandmarkDto.setDescription("AnotherTestDescription");
        anotherLandmarkDto.setServices(serviceDtoList);
        anotherLandmarkDto.setSettlement(settlementDto);

        landmarkDtoList = new ArrayList<>();
        landmarkDtoList.add(landmarkDto);
        landmarkDtoList.add(anotherLandmarkDto);

        settlementDto = new SettlementDto();
        settlementDto.setId(1L);
        settlementDto.setName("Test settlement");
        settlementDto.setPopulation(2000000L);
        settlementDto.setHasSubway(true);
        settlementDto.setLandmarks(landmarkDtoList);

        incorrectDtoForUpdate = new SettlementDto();
        incorrectDtoForUpdate.setId(2L);
        incorrectDtoForUpdate.setName("Test incorrect settlement");
        incorrectDtoForUpdate.setPopulation(3000000L);
        incorrectDtoForUpdate.setHasSubway(true);
        incorrectDtoForUpdate.setLandmarks(landmarkDtoList);

        dtoForCreate = new SettlementDto();
        dtoForCreate.setName("Test for Create");
        dtoForCreate.setPopulation(2000000L);
        dtoForCreate.setHasSubway(true);
        dtoForCreate.setLandmarks(landmarkDtoList);

        entityForCreate = new Settlement();
        entityForCreate.setName("Test for Create");
        entityForCreate.setPopulation(2000000L);
        entityForCreate.setHasSubway(true);
        entityForCreate.setLandmarks(landmarks);

        createdEntity = new Settlement();
        createdEntity.setId(1L);
        createdEntity.setName("Test for Create");
        createdEntity.setPopulation(2000000L);
        createdEntity.setHasSubway(true);
        createdEntity.setLandmarks(landmarks);

        createdDto = new SettlementDto();
        createdDto.setId(1L);
        createdDto.setName("Test dtoForCreate");
        createdDto.setPopulation(2000000L);
        createdDto.setHasSubway(true);
        createdDto.setLandmarks(landmarkDtoList);
    }

    @Test
    void testCreateSettlement() {

        when(mapper.toEntity(dtoForCreate)).thenReturn(entityForCreate);
        when(repository.save(entityForCreate)).thenReturn(createdEntity);
        when(mapper.toDto(createdEntity)).thenReturn(createdDto);

        SettlementDto createdSettlement = settlementService.create(dtoForCreate);

        verify(repository).save(entityForCreate);
        assertEquals(createdDto, createdSettlement);
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

        when(repository.findById(incorrectDtoForUpdate.getId())).thenReturn(Optional.of(settlement));
        when(mapper.toEntity(settlementDto)).thenReturn(settlement);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);

        Throwable thrown = catchThrowable(() -> settlementService.update(incorrectDtoForUpdate));

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
