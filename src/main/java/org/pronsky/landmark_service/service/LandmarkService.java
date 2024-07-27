package org.pronsky.landmark_service.service;

import org.pronsky.landmark_service.service.dto.LandmarkDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;

import java.util.Set;

public interface LandmarkService {
    Set<LandmarkDto> getAllByTypeSorted(LandmarkDto.LandmarkType type, String sortingParam);
    Set<LandmarkDto> getAllBySettlement(SettlementDto settlement);
    LandmarkDto create(LandmarkDto dto);
    LandmarkDto update(LandmarkDto dto);
    void delete(LandmarkDto dto);
}
