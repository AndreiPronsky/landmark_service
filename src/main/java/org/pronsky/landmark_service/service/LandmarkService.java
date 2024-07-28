package org.pronsky.landmark_service.service;

import org.pronsky.landmark_service.service.dto.LandmarkDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;

import java.util.List;

public interface LandmarkService {
    List<LandmarkDto> getAllByTypeSorted(String landmarkType, String sortingParam);

    List<LandmarkDto> getAllBySettlement(SettlementDto settlement);

    LandmarkDto create(LandmarkDto dto);

    LandmarkDto update(LandmarkDto dto);

    void delete(LandmarkDto dto);
}
