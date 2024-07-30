package org.pronsky.landmark_service.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "landmarks", joinColumns = @JoinColumn(name = "landmark_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Landmark> landmarks;
}
