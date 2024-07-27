package org.pronsky.landmark_service.service.mapper;

import org.mapstruct.Mapper;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.entities.Settlement;
import org.pronsky.landmark_service.service.dto.LandmarkDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    LandmarkDto toDto(Landmark entity);
    Landmark toEntity(LandmarkDto dto);
    SettlementDto toDto(Settlement entity);
    Settlement toEntity(SettlementDto dto);
}
