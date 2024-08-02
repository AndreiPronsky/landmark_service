package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    List<Landmark> findAllBySettlementName(String settlementName);

    List<Landmark> findAllByType(Landmark.LandmarkType landmarkType);
}
