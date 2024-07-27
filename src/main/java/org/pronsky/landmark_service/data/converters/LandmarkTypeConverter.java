package org.pronsky.landmark_service.data.converters;

import jakarta.persistence.AttributeConverter;
import org.pronsky.landmark_service.data.entities.Landmark;

public class LandmarkTypeConverter implements AttributeConverter<Landmark.LandmarkType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Landmark.LandmarkType landmarkType) {
        int id;
        switch (landmarkType) {
            case ARCHITECTURE -> id = 1;
            case MUSEUMS_AND_GALLERIES -> id = 2;
            case BEACHES_AND_PARKS -> id = 3;
            case ARCHEOLOGICAL -> id = 4;
            case HISTORICAL -> id = 5;
            default -> throw new IllegalArgumentException("Invalid landmarkType: " + landmarkType);
        }
        return id;
    }

    @Override
    public Landmark.LandmarkType convertToEntityAttribute(Integer typeId) {
        Landmark.LandmarkType type;
        switch (typeId) {
            case 1 -> type = Landmark.LandmarkType.ARCHITECTURE;
            case 2 -> type = Landmark.LandmarkType.MUSEUMS_AND_GALLERIES;
            case 3 -> type = Landmark.LandmarkType.BEACHES_AND_PARKS;
            case 4 -> type = Landmark.LandmarkType.ARCHEOLOGICAL;
            case 5 -> type = Landmark.LandmarkType.HISTORICAL;
            default -> throw new IllegalArgumentException("Invalid typeId: " + typeId);
        }
        return type;
    }
}
