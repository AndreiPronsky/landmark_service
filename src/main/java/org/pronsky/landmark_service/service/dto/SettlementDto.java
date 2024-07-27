package org.pronsky.landmark_service.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SettlementDto {
    private Long id;
    private String name;
    private Long population;
    private Set<LandmarkDto> landmarks;
    private boolean hasSubway;
}
