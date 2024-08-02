package org.pronsky.landmark_service.service.mapper;

import org.mapstruct.Mapper;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.entities.Service;
import org.pronsky.landmark_service.data.entities.Settlement;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.pronsky.landmark_service.service.dto.ServiceDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    default LandmarkFullDto toFullLandmarkDto(LandmarkTrimmedDto trimmed) {
        LandmarkFullDto landmarkFullDto = new LandmarkFullDto();
        landmarkFullDto.setId(trimmed.getId());
        landmarkFullDto.setName(trimmed.getName());
        landmarkFullDto.setDescription(trimmed.getDescription());
        landmarkFullDto.setServices(trimmed.getServices());
        landmarkFullDto.setType(LandmarkFullDto.LandmarkType.valueOf(trimmed.getType().name()));
        landmarkFullDto.setCreationYear(trimmed.getCreationYear());
        return landmarkFullDto;
    }
    LandmarkFullDto toDto(Landmark entity);
    Landmark toEntity(LandmarkFullDto dto);
    SettlementDto toDto(Settlement entity);
    Settlement toEntity(SettlementDto dto);
    ServiceDto toDto(Service entity);
    Service toEntity(ServiceDto dto);
}
