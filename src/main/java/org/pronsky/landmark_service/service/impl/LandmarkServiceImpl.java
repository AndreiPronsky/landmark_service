package org.pronsky.landmark_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class LandmarkServiceImpl implements LandmarkService {
    @Override
    public Set<LandmarkDto> getAllByTypeSorted(LandmarkDto.LandmarkType type, String sortingParam) {
        return Set.of();
    }

    @Override
    public Set<LandmarkDto> getAllBySettlement(SettlementDto settlement) {
        return Set.of();
    }

    @Override
    public LandmarkDto create(LandmarkDto dto) {
        return null;
    }

    @Override
    public LandmarkDto update(LandmarkDto dto) {
        return null;
    }

    @Override
    public void delete(LandmarkDto dto) {
        throw new UnsupportedOperationException("Delete not supported");
    }
}
