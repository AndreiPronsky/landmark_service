package org.pronsky.landmark_service.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "settlements")
public class Settlement {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private Long population;

    @OneToMany
    private Set<Landmark> landmarks;
    private boolean hasSubway;
}
