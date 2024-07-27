package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
}
