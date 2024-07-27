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
    public SettlementDto create(SettlementDto settlementDto) {
        return mapper.toDto(repository.save(mapper.toEntity(settlementDto)));
    }

    @Override
    public SettlementDto update(SettlementDto settlementDto) {
        SettlementDto fetched = mapper.toDto(repository.findById(settlementDto.getId())
                .orElseThrow(UnsupportedOperationException::new));
        if (allMatchExceptPopulationAndSubway(settlementDto, fetched)) {
            return mapper.toDto(repository.save(mapper.toEntity(settlementDto)));
        }
        throw new UnsupportedOperationException("Forbidden to update anything except subway and population");
    }

    private boolean allMatchExceptPopulationAndSubway(SettlementDto forUpdate, SettlementDto fetched) {
        return !(forUpdate.isHasSubway() == fetched.isHasSubway() ||
                forUpdate.getPopulation().equals(fetched.getPopulation())) &&
                forUpdate.getName().equals(fetched.getName()) &&
                forUpdate.getLandmarks().equals(fetched.getLandmarks());
    }
}
