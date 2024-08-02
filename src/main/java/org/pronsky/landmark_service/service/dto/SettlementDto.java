package org.pronsky.landmark_service.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class SettlementDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("population")
    private Long population;
    @JsonProperty("landmarks")
    private Set<LandmarkFullDto> landmarks;
    @JsonProperty("hasSubway")
    private boolean hasSubway;
}
