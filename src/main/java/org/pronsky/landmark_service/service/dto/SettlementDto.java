package org.pronsky.landmark_service.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "landmarks")
public class SettlementDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("population")
    private Long population;
    @JsonProperty("landmarks")
    private List<LandmarkFullDto> landmarks;
    @JsonProperty("hasSubway")
    private boolean hasSubway;
}
