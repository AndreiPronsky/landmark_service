package org.pronsky.landmark_service.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LandmarkTrimmedDto extends LandmarkDto {
    @JsonProperty("type")
    private LandmarkType type;
    @JsonProperty("settlementName")
    private String settlementName;

    public enum LandmarkType {
        ARCHITECTURE,
        MUSEUMS_AND_GALLERIES,
        BEACHES_AND_PARKS,
        ARCHEOLOGICAL,
        HISTORICAL
    }
}
