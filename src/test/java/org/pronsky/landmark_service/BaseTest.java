package org.pronsky.landmark_service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.entities.Service;
import org.pronsky.landmark_service.data.entities.Settlement;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.pronsky.landmark_service.service.dto.ServiceDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class is created to provide required dependencies and to initialize required objects
 */
@SpringBootTest
public class BaseTest {
    protected static final String LANDMARK_TYPE = "HISTORICAL";
    protected static final String NAME_SORTING_PARAM = "name";
    protected static final String SETTLEMENT_SORTING_PARAM = "settlementName";
    protected static final String CREATION_YEAR_SORTING_PARAM = "creationYear";

    protected static Service service;
    protected static List<Service> services;
    protected static ServiceDto serviceDto;
    protected static List<ServiceDto> serviceDtoList;

    protected static Landmark landmark;
    protected static Landmark landmark1;
    protected static Landmark anotherLandmark;
    protected static Landmark landmarkForUpdate;
    protected static Landmark landmarkForCreate;
    protected static Landmark createdLandmark;
    protected static List<Landmark> landmarksSortedByName;
    protected static List<Landmark> landmarksSortedBySettlement;
    protected static List<Landmark> landmarksSortedByYear;
    protected static List<Landmark> sameSettlementLandmarkList;
    protected static LandmarkFullDto landmarkDto;
    protected static LandmarkFullDto landmarkDto1;
    protected static LandmarkFullDto anotherLandmarkDto;
    protected static List<LandmarkFullDto> landmarkDtoList;
    protected static List<LandmarkFullDto> sameSettlementLandmarkDtoList;
    protected static LandmarkTrimmedDto correctTrimmedForUpdate;
    protected static LandmarkTrimmedDto incorrectDtoForUpdate;
    protected static LandmarkTrimmedDto trimmedDtoForCreate;
    protected static LandmarkFullDto fullDtoForCreate;
    protected static LandmarkFullDto fullDtoForUpdate;
    protected static LandmarkFullDto incorrectFullDtoForUpdate;
    protected static LandmarkFullDto correctFullForUpdate;

    protected static SettlementDto settlementDto;
    protected static SettlementDto anotherSettlementDto;
    protected static SettlementDto settlementDtoForCreate;
    protected static Settlement settlementForCreate;
    protected static SettlementDto incorrectSettlementDtoForUpdate;
    protected static Settlement createdSettlement;
    protected static SettlementDto createdSettlementDto;
    protected static Settlement settlement;
    protected static Settlement anotherSettlement;

    protected static PostgreSQLContainer<?> postgres;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

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
        settlement.setName("A Test settlement");
        settlement.setPopulation(2000000L);
        settlement.setHasSubway(true);

        anotherSettlement = new Settlement();
        anotherSettlement.setId(2L);
        anotherSettlement.setName("B test settlement");
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

        landmark1 = new Landmark();
        landmark1.setId(3L);
        landmark1.setName("Test landmark C");
        landmark1.setType(Landmark.LandmarkType.HISTORICAL);
        landmark1.setCreationYear((short) 1999);
        landmark1.setDescription("TestDescription");
        landmark1.setServices(services);
        landmark1.setSettlement(settlement);

        sameSettlementLandmarkList = new ArrayList<>();
        sameSettlementLandmarkList.add(landmark);
        sameSettlementLandmarkList.add(landmark1);

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
        landmarksSortedBySettlement.add(landmark);
        landmarksSortedBySettlement.add(anotherLandmark);

        landmarksSortedByYear = new ArrayList<>();
        landmarksSortedByYear.add(landmark);
        landmarksSortedByYear.add(anotherLandmark);

        serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setName("Test service");
        serviceDto.setDescription("Test description");
        serviceDto.setLandmarkId(1L);

        serviceDtoList = new ArrayList<>();
        serviceDtoList.add(serviceDto);

        settlementDto = new SettlementDto();
        settlementDto.setId(1L);
        settlementDto.setName("A Test settlement");
        settlementDto.setPopulation(2000000L);
        settlementDto.setHasSubway(true);

        anotherSettlementDto = new SettlementDto();
        anotherSettlementDto.setId(2L);
        anotherSettlementDto.setName("B Test settlement");
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

        landmarkDto1 = new LandmarkFullDto();
        landmarkDto1.setId(1L);
        landmarkDto1.setName("Test landmark C");
        landmarkDto1.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        landmarkDto1.setCreationYear((short) 1999);
        landmarkDto1.setDescription("TestDescription");
        landmarkDto1.setServices(serviceDtoList);
        landmarkDto1.setSettlement(settlementDto);

        sameSettlementLandmarkDtoList = new ArrayList<>();
        sameSettlementLandmarkDtoList.add(landmarkDto);
        sameSettlementLandmarkDtoList.add(landmarkDto1);

        anotherLandmarkDto = new LandmarkFullDto();
        anotherLandmarkDto.setId(2L);
        anotherLandmarkDto.setName("Test landmark B");
        anotherLandmarkDto.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        anotherLandmarkDto.setCreationYear((short) 2000);
        anotherLandmarkDto.setDescription("AnotherTestDescription");
        anotherLandmarkDto.setServices(serviceDtoList);
        anotherLandmarkDto.setSettlement(anotherSettlementDto);

        landmarkDtoList = new ArrayList<>();
        landmarkDtoList.add(landmarkDto);
        landmarkDtoList.add(anotherLandmarkDto);

        correctTrimmedForUpdate = new LandmarkTrimmedDto();
        correctTrimmedForUpdate.setId(1L);
        correctTrimmedForUpdate.setName("Test landmark A");
        correctTrimmedForUpdate.setType(LandmarkTrimmedDto.LandmarkType.HISTORICAL);
        correctTrimmedForUpdate.setCreationYear((short) 1999);
        correctTrimmedForUpdate.setDescription("Test Update Description");
        correctTrimmedForUpdate.setServices(serviceDtoList);
        correctTrimmedForUpdate.setSettlementName("Test settlement");

        correctFullForUpdate = new LandmarkFullDto();
        correctFullForUpdate.setId(1L);
        correctFullForUpdate.setName("Test landmark A");
        correctFullForUpdate.setType(LandmarkFullDto.LandmarkType.HISTORICAL);
        correctFullForUpdate.setCreationYear((short) 1999);
        correctFullForUpdate.setDescription("Test Update Description");
        correctFullForUpdate.setServices(serviceDtoList);
        correctFullForUpdate.setSettlement(settlementDto);

        landmarkForUpdate = new Landmark();
        landmarkForUpdate.setId(1L);
        landmarkForUpdate.setName("Test landmark A");
        landmarkForUpdate.setType(Landmark.LandmarkType.HISTORICAL);
        landmarkForUpdate.setCreationYear((short) 1999);
        landmarkForUpdate.setDescription("Test Update Description");
        landmarkForUpdate.setServices(services);
        landmarkForUpdate.setSettlement(settlement);

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

        landmarkForCreate = new Landmark();
        landmarkForCreate.setName("Test landmark A");
        landmarkForCreate.setType(Landmark.LandmarkType.HISTORICAL);
        landmarkForCreate.setCreationYear((short) 1999);
        landmarkForCreate.setDescription("TestDescription");
        landmarkForCreate.setServices(services);
        landmarkForCreate.setSettlement(settlement);

        createdLandmark = new Landmark();
        createdLandmark.setId(1L);
        createdLandmark.setName("Test landmark A");
        createdLandmark.setType(Landmark.LandmarkType.HISTORICAL);
        createdLandmark.setCreationYear((short) 1999);
        createdLandmark.setDescription("TestDescription");
        createdLandmark.setServices(services);
        createdLandmark.setSettlement(settlement);

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

        incorrectSettlementDtoForUpdate = new SettlementDto();
        incorrectSettlementDtoForUpdate.setId(2L);
        incorrectSettlementDtoForUpdate.setName("Test incorrect settlement");
        incorrectSettlementDtoForUpdate.setPopulation(3000000L);
        incorrectSettlementDtoForUpdate.setHasSubway(true);

        settlementDtoForCreate = new SettlementDto();
        settlementDtoForCreate.setName("Test for Create");
        settlementDtoForCreate.setPopulation(2000000L);
        settlementDtoForCreate.setHasSubway(true);

        settlementForCreate = new Settlement();
        settlementForCreate.setName("Test for Create");
        settlementForCreate.setPopulation(2000000L);
        settlementForCreate.setHasSubway(true);

        createdSettlement = new Settlement();
        createdSettlement.setId(1L);
        createdSettlement.setName("Test for Create");
        createdSettlement.setPopulation(2000000L);
        createdSettlement.setHasSubway(true);

        createdSettlementDto = new SettlementDto();
        createdSettlementDto.setId(1L);
        createdSettlementDto.setName("Test dtoForCreate");
        createdSettlementDto.setPopulation(2000000L);
        createdSettlementDto.setHasSubway(true);

        postgres = new PostgreSQLContainer<>("postgres");
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
}


