package org.pronsky.landmark_service.service;

import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;

import java.util.List;

public interface LandmarkService {
    List<LandmarkFullDto> getAllByTypeSorted(String landmarkType, String sortingParam);

    List<LandmarkFullDto> getAllBySettlement(String settlement);

    LandmarkFullDto create(LandmarkTrimmedDto dto);

    LandmarkFullDto update(LandmarkTrimmedDto dto);

    void delete(Long id);
}
