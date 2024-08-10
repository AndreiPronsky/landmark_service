package org.pronsky.landmark_service.data.converters;

import org.junit.jupiter.api.Test;
import org.pronsky.landmark_service.BaseTest;
import org.pronsky.landmark_service.data.entities.Landmark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LandmarkTypeConverterTest extends BaseTest {

    private final LandmarkTypeConverter converter = new LandmarkTypeConverter();

    @Test
    void testConvertToDatabaseColumn() {
        assertEquals(1, converter.convertToDatabaseColumn(Landmark.LandmarkType.ARCHITECTURE));
        assertEquals(2, converter.convertToDatabaseColumn(Landmark.LandmarkType.MUSEUMS_AND_GALLERIES));
        assertEquals(3, converter.convertToDatabaseColumn(Landmark.LandmarkType.BEACHES_AND_PARKS));
        assertEquals(4, converter.convertToDatabaseColumn(Landmark.LandmarkType.ARCHEOLOGICAL));
        assertEquals(5, converter.convertToDatabaseColumn(Landmark.LandmarkType.HISTORICAL));
        assertThrows(NullPointerException.class, () -> converter.convertToDatabaseColumn(null));
    }

    @Test
    void testConvertToEntityAttribute() {
        assertEquals(Landmark.LandmarkType.ARCHITECTURE, converter.convertToEntityAttribute(1));
        assertEquals(Landmark.LandmarkType.MUSEUMS_AND_GALLERIES, converter.convertToEntityAttribute(2));
        assertEquals(Landmark.LandmarkType.BEACHES_AND_PARKS, converter.convertToEntityAttribute(3));
        assertEquals(Landmark.LandmarkType.ARCHEOLOGICAL, converter.convertToEntityAttribute(4));
        assertEquals(Landmark.LandmarkType.HISTORICAL, converter.convertToEntityAttribute(5));
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute(0));
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute(6));
        assertThrows(NullPointerException.class, () -> converter.convertToEntityAttribute(null));
    }
}
