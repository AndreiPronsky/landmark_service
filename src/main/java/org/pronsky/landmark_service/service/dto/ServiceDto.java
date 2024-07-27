package org.pronsky.landmark_service.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServiceDto {
    private Long id;
    private String name;
    private String description;
    private List<LandmarkDto> landmarks;
}
