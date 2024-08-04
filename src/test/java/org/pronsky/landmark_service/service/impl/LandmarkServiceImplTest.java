package org.pronsky.landmark_service.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.entities.Service;
import org.pronsky.landmark_service.data.entities.Settlement;
import org.pronsky.landmark_service.data.repository.LandmarkRepository;
import org.pronsky.landmark_service.data.repository.ServiceRepository;
import org.pronsky.landmark_service.data.repository.SettlementRepository;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.pronsky.landmark_service.service.dto.ServiceDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class LandmarkServiceImplTest {

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

    private static final String LANDMARK_TYPE = "HISTORICAL";
    private static final String NAME_SORTING_PARAM = "name";
    private static final String SETTLEMENT_SORTING_PARAM = "settlementName";
    private static final String CREATION_YEAR_SORTING_PARAM = "creationYear";
    private static Service service;
    private static List<Service> services;
    private static Landmark landmark;
    private static Landmark anotherLandmark;
    private static Landmark forUpdate;
    private static Landmark forCreate;
    private static Landmark created;
    private static List<Landmark> landmarksSortedByName;
    private static List<Landmark> landmarksSortedBySettlement;
    private static List<Landmark> landmarksSortedByYear;
    private static Settlement settlement;
    private static Settlement anotherSettlement;
    private static LandmarkFullDto landmarkDto;
    private static LandmarkFullDto anotherLandmarkDto;
    private static ServiceDto serviceDto;
    private static List<ServiceDto> serviceDtoList;
    private static SettlementDto settlementDto;
    private static SettlementDto anotherSettlementDto;
    private static List<LandmarkFullDto> dtoSet;
    private static LandmarkTrimmedDto correctDtoForUpdate;
    private static LandmarkTrimmedDto incorrectDtoForUpdate;
    private static LandmarkTrimmedDto trimmedDtoForCreate;
    private static LandmarkFullDto fullDtoForCreate;
    private static LandmarkFullDto fullDtoForUpdate;
    private static LandmarkFullDto incorrectFullDtoForUpdate;

    @BeforeAll
    static void setUp() {
        service = new Service();
        service.setId(1L);
        service.setName("Test service");
        service.setDescription("Test description");
        service.setLandmarkId(1L);

        services = new ArrayList<>();
        services.add(service);

        settlement = new Settlement();
        settlement.setId(1L);
        settlement.setName("Test settlement");
        settlement.setPopulation(2000000L);
        settlement.setHasSubway(true);

        anotherSettlement = new Settlement();
        anotherSettlement.setId(2L);
        anotherSettlement.setName("Another test settlement");
        anotherSettlement.setPopulation(2000000L);
        anotherSettlement.setHasSubway(true);

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
        anotherLandmark.setSettlement(anotherSettlement);

        landmarksSortedByName = new ArrayList<>();
        landmarksSortedByName.add(landmark);
        landmarksSortedByName.add(anotherLandmark);

        landmarksSortedBySettlement = new ArrayList<>();
        landmarksSortedBySettlement.add(anotherLandmark);
        landmarksSortedBySettlement.add(landmark);

        landmarksSortedByYear = new ArrayList<>();
        landmarksSortedByYear.add(landmark);
        landmarksSortedByYear.add(anotherLandmark);

        settlement.setLandmarks(landmarksSortedByName);

        serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setName("Test service");
        serviceDto.setDescription("Test description");
        serviceDto.setLandmarkId(1L);

        serviceDtoList = new ArrayList<>();
        serviceDtoList.add(serviceDto);

        settlementDto = new SettlementDto();
        settlementDto.setId(1L);
        settlementDto.setName("Test settlement");
        settlementDto.setPopulation(2000000L);
        settlementDto.setHasSubway(true);

        anotherSettlementDto = new SettlementDto();
        anotherSettlementDto.setId(2L);
        anotherSettlementDto.setName("Another test settlement");
        anotherSettlementDto.setPopulation(2000000L);
        anotherSettlementDto.setHasSubway(true);

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
        anotherLandmarkDto.setSettlement(anotherSettlementDto);

        dtoSet = new ArrayList<>();
        dtoSet.add(landmarkDto);
        dtoSet.add(anotherLandmarkDto);

        settlementDto.setLandmarks(dtoSet);

        correctDtoForUpdate = new LandmarkTrimmedDto();
        correctDtoForUpdate.setId(1L);
        correctDtoForUpdate.setName("Test landmark A");
        correctDtoForUpdate.setType(LandmarkTrimmedDto.LandmarkType.HISTORICAL);
        correctDtoForUpdate.setCreationYear((short) 1999);
        correctDtoForUpdate.setDescription("Test Update Description");
        correctDtoForUpdate.setServices(serviceDtoList);
        correctDtoForUpdate.setSettlementName("Test settlement");

        forUpdate = new Landmark();
        forUpdate.setId(1L);
        forUpdate.setName("Test landmark A");
        forUpdate.setType(Landmark.LandmarkType.HISTORICAL);
        forUpdate.setCreationYear((short) 1999);
        forUpdate.setDescription("Test Update Description");
        forUpdate.setServices(services);
        forUpdate.setSettlement(settlement);

        incorrectDtoForUpdate = new LandmarkTrimmedDto();
        incorrectDtoForUpdate.setId(1L);
        incorrectDtoForUpdate.setName("Test landmark update name");
        incorrectDtoForUpdate.setType(LandmarkTrimmedDto.LandmarkType.ARCHEOLOGICAL);
        incorrectDtoForUpdate.setCreationYear((short) 3000);
        incorrectDtoForUpdate.setDescription("Test Update Description");
        incorrectDtoForUpdate.setServices(serviceDtoList);
        incorrectDtoForUpdate.setSettlementName("Test settlement");

        incorrectFullDtoForUpdate = new LandmarkFullDto();
        incorrectFullDtoForUpdate.setId(1L);
        incorrectFullDtoForUpdate.setName("Test landmark update name");
        incorrectFullDtoForUpdate.setType(LandmarkFullDto.LandmarkType.ARCHEOLOGICAL);
        incorrectFullDtoForUpdate.setCreationYear((short) 3000);
        incorrectFullDtoForUpdate.setDescription("Test Update Description");
        incorrectFullDtoForUpdate.setServices(serviceDtoList);
        incorrectFullDtoForUpdate.setSettlement(settlementDto);

        forCreate = new Landmark();
        forCreate.setName("Test landmark A");
        forCreate.setType(Landmark.LandmarkType.HISTORICAL);
        forCreate.setCreationYear((short) 1999);
        forCreate.setDescription("TestDescription");
        forCreate.setServices(services);
        forCreate.setSettlement(settlement);

        created = new Landmark();
        created.setId(1L);
        created.setName("Test landmark A");
        created.setType(Landmark.LandmarkType.HISTORICAL);
        created.setCreationYear((short) 1999);
        created.setDescription("TestDescription");
        created.setServices(services);
        created.setSettlement(settlement);

        trimmedDtoForCreate = new LandmarkTrimmedDto();
        trimmedDtoForCreate.setName("Test landmark A");
        trimmedDtoForCreate.setType(LandmarkTrimmedDto.LandmarkType.HISTORICAL);
        trimmedDtoForCreate.setCreationYear((short) 1999);
        trimmedDtoForCreate.setDescription("TestDescription");
        trimmedDtoForCreate.setServices(serviceDtoList);
        trimmedDtoForCreate.setSettlementName("Settlement name");

        fullDtoForCreate = new LandmarkFullDto();
        fullDtoForCreate.setName("Test landmark A");
        fullDtoForCreate.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        fullDtoForCreate.setCreationYear((short) 1999);
        fullDtoForCreate.setDescription("TestDescription");
        fullDtoForCreate.setServices(serviceDtoList);
        fullDtoForCreate.setSettlement(settlementDto);

        fullDtoForUpdate = new LandmarkFullDto();
        fullDtoForUpdate.setId(1L);
        fullDtoForUpdate.setName("Test landmark A");
        fullDtoForUpdate.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        fullDtoForUpdate.setCreationYear((short) 1999);
        fullDtoForUpdate.setDescription("Test Update Description");
        fullDtoForUpdate.setServices(serviceDtoList);
        fullDtoForUpdate.setSettlement(settlementDto);
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllByTypeSorted_ByName() {
        when(landmarkRepository.findAllByTypeOrderByName(Landmark.LandmarkType.valueOf(LANDMARK_TYPE)))
                .thenReturn(landmarksSortedByName);
        when(mapper.toDto(landmark)).thenReturn(landmarkDto);
        when(mapper.toDto(anotherLandmark)).thenReturn(anotherLandmarkDto);

        List<LandmarkFullDto> result = landmarkService.getAllByTypeSorted(LANDMARK_TYPE, NAME_SORTING_PARAM);

        assertEquals(result.size(), landmarksSortedByName.size());
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
    void testUpdate_FullUpdate_ThrowsException() {
        when(landmarkRepository.findById(incorrectDtoForUpdate.getId()))
                .thenReturn(Optional.ofNullable(forUpdate));
        when(settlementRepository.findByName(incorrectDtoForUpdate.getSettlementName()))
                .thenReturn(settlement);
        when(mapper.toDto(forUpdate)).thenReturn(landmarkDto);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);
        when(mapper.toFullLandmarkDto(incorrectDtoForUpdate)).thenReturn(incorrectFullDtoForUpdate);

        Throwable thrown = catchThrowable(() -> landmarkService.update(incorrectDtoForUpdate));

        assertThat(thrown)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Forbidden");
    }

    @Test
    void testUpdate_DescriptionUpdate() {
        when(landmarkRepository.findById(forUpdate.getId())).thenReturn(Optional.ofNullable(forUpdate));
        when(settlementRepository.findByName(correctDtoForUpdate.getSettlementName())).thenReturn(settlement);
        when(serviceRepository.save(any(Service.class))).thenReturn(service);
        when(landmarkRepository.save(any(Landmark.class))).thenReturn(forUpdate);

        when(mapper.toDto(forUpdate)).thenReturn(fullDtoForUpdate);
        when(mapper.toDto(service)).thenReturn(serviceDto);
        when(mapper.toDto(settlement)).thenReturn(settlementDto);
        when(mapper.toEntity(fullDtoForUpdate)).thenReturn(forUpdate);
        when(mapper.toEntity(serviceDto)).thenReturn(service);
        when(mapper.toEntity(settlementDto)).thenReturn(settlement);
        when(mapper.toFullLandmarkDto(correctDtoForUpdate)).thenReturn(fullDtoForUpdate);

        LandmarkFullDto updated = landmarkService.update(correctDtoForUpdate);

        assertEquals(updated.getId(), correctDtoForUpdate.getId());
        verify(landmarkRepository, times(1)).save(any(Landmark.class));
    }

    @Test
    void testCreate() {
        when(settlementRepository.findByName(trimmedDtoForCreate.getSettlementName())).thenReturn(settlement);
        when(landmarkRepository.save(forCreate)).thenReturn(created);
        when(serviceRepository.save(service)).thenReturn(service);
        when(mapper.toDto(created)).thenReturn(landmarkDto);
        when(mapper.toEntity(any(LandmarkFullDto.class))).thenReturn(forCreate);
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
