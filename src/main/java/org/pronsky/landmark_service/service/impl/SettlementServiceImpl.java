package org.pronsky.landmark_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.data.repository.SettlementRepository;
import org.pronsky.landmark_service.service.SettlementService;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {
    private final EntityDtoMapper mapper;
    private final SettlementRepository repository;

    @Override
    public SettlementDto create(SettlementDto dto) {
        log.info("METHOD CALL: SettlementService.create({})", dto);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public SettlementDto update(SettlementDto dto) {
        log.info("METHOD CALL: SettlementService.update({})", dto);
        SettlementDto fetched = mapper.toDto(repository.findById(dto.getId())
                .orElseThrow(NullPointerException::new));
        if (allMatchExceptPopulationAndSubway(dto, fetched)) {
            return mapper.toDto(repository.save(mapper.toEntity(dto)));
        }
        throw new UnsupportedOperationException("Forbidden to update anything except subway and population");
    }

    private boolean allMatchExceptPopulationAndSubway(SettlementDto forUpdate, SettlementDto fetched) {
        return (forUpdate.getName().equals(fetched.getName()) &&
                forUpdate.getLandmarks().equals(fetched.getLandmarks()));
    }
}
