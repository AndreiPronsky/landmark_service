package org.pronsky.landmark_service.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.pronsky.landmark_service.data.converters.LandmarkTypeConverter;

import java.util.List;

@Data
@Entity
@Table(name = "landmarks")
public class Landmark {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_year")
    private Short creationYear;

    @Column(name = "description")
    private String description;

    @Column(name = "type_id")
    @Convert(converter = LandmarkTypeConverter.class)
    private LandmarkType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Settlement settlement;

    @OneToMany
    private List<Service> services;

    @Table(name = "landmark_types")
    public enum LandmarkType {
        ARCHITECTURE,
        MUSEUMS_AND_GALLERIES,
        BEACHES_AND_PARKS,
        ARCHEOLOGICAL,
        HISTORICAL
    }
}
