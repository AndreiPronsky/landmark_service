package org.pronsky.landmark_service.data.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "landmarks")
@Entity
@Table(name = "settlements")
public class Settlement {

    @Id
    @Column(name = "settlement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "population")
    private Long population;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Landmark> landmarks;

    @Column(name = "has_subway")
    private boolean hasSubway;
}
