package org.pronsky.landmark_service.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LandmarkDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("creationYear")
    private Short creationYear;
    @JsonProperty("description")
    private String description;
    @JsonProperty("services")
    private List<ServiceDto> services;
}
