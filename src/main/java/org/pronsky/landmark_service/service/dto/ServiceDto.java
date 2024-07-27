package org.pronsky.landmark_service.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ServiceDto {
    private Long id;
    private String name;
    private String description;
    private Set<LandmarkDto> landmarks;
}
