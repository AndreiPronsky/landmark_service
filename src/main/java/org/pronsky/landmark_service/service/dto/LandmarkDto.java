package org.pronsky.landmark_service.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class LandmarkDto {
    private Long id;
    private String name;
    private Short creationYear;
    private String description;
    private LandmarkType type;
    private SettlementDto settlement;
    private Set<ServiceDto> services;

    public enum LandmarkType {
        ARCHITECTURE,
        MUSEUMS_AND_GALLERIES,
        BEACHES_AND_PARKS,
        ARCHEOLOGICAL,
        HISTORICAL
    }
}
