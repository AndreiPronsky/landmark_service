package org.pronsky.landmark_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.repository.LandmarkRepository;
import org.pronsky.landmark_service.data.repository.ServiceRepository;
import org.pronsky.landmark_service.data.repository.SettlementRepository;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.pronsky.landmark_service.service.dto.ServiceDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
    private final LandmarkRepository landmarkRepository;
    private final ServiceRepository serviceRepository;
    private final SettlementRepository settlementRepository;
    private final EntityDtoMapper mapper;

    @Override
    public List<LandmarkFullDto> getAllByTypeSorted(String landmarkType, String sortingParam) {
        log.info("METHOD CALL: LandmarkService.getAllByTypeSorted({}, {})", landmarkType, sortingParam);
        List<LandmarkFullDto> dtoList = landmarkRepository
                .findAllByType(Landmark.LandmarkType.valueOf(landmarkType))
                .stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(LandmarkFullDto::getName))
                .collect(Collectors.toList());
        if (sortingParam.equals("desc")) {
            Collections.reverse(dtoList);
        }
        log.info("LandmarkService.getAllByTypeSorted() returned {}", dtoList);
        return dtoList;
    }

    @Override
    public List<LandmarkFullDto> getAllBySettlement(String settlement) {
        log.info("METHOD CALL: LandmarkService.getAllBySettlement({})", settlement);
        return landmarkRepository.findAllBySettlementName(settlement)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public LandmarkFullDto create(LandmarkTrimmedDto dto) {
        log.info("METHOD CALL: LandmarkService.create({})", dto);
        List<ServiceDto> services = createServices(dto);
        LandmarkFullDto fullDto = mapper.toFullLandmarkDto(dto);
        SettlementDto settlement = mapper.toDto(settlementRepository.findByName(dto.getSettlementName()));
        fullDto.setSettlement(settlement);
        LandmarkFullDto saved = mapper.toDto(landmarkRepository.save(mapper.toEntity(fullDto)));
        services = setLandmarkIdAndSaveServices(services, saved);
        saved.setServices(services);
        return saved;
    }

    @Override
    public LandmarkFullDto update(LandmarkTrimmedDto dto) {
        log.info("METHOD CALL: LandmarkService.update({})", dto);
        LandmarkFullDto fetched = mapper.toDto(landmarkRepository.findById(dto.getId())
                .orElseThrow(UnsupportedOperationException::new));
        if (allSameExceptDescription(dto, fetched)) {
            LandmarkFullDto fullDto = mapper.toFullLandmarkDto(dto);
            fullDto.setSettlement(mapper.toDto(settlementRepository.findByName(dto.getName())));
            return mapper.toDto(landmarkRepository.save(mapper.toEntity(fullDto)));
        }
        throw new UnsupportedOperationException("Forbidden to update anything except description");
    }

    @Override
    public void delete(Long id) {
        log.info("METHOD CALL: LandmarkService.delete({})", id);
        landmarkRepository.deleteById(id);
    }

    private boolean allSameExceptDescription(LandmarkTrimmedDto forUpdate, LandmarkFullDto fetched) {
        return (!forUpdate.getDescription().equals(fetched.getDescription()) &&
                forUpdate.getName().equals(fetched.getName()) &&
                forUpdate.getType().name().equals(fetched.getType().name()) &&
                forUpdate.getCreationYear().equals(fetched.getCreationYear()) &&
                forUpdate.getServices().equals(fetched.getServices()) &&
                forUpdate.getSettlementName().equals(fetched.getSettlement().getName()));
    }

    private List<ServiceDto> createServices(LandmarkTrimmedDto dto) {
        List<ServiceDto> services = serviceRepository
                .saveAll(dto.getServices()
                        .stream()
                        .map(mapper::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        dto.setServices(services);
        return services;
    }

    private List<ServiceDto> setLandmarkIdAndSaveServices(List<ServiceDto> services, LandmarkFullDto saved) {
        services = services.stream()
                .peek(service -> service.setLandmarkId(saved.getId()))
                .toList();
        serviceRepository.saveAll(services.stream().map(mapper::toEntity).toList());
        return services;
    }
}
