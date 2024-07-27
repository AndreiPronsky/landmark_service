package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    Set<Landmark> findAllBySettlementId(Long settlementId);
    Set<Landmark> findAllBySettlementIdAndType(Long settlementId, Landmark.LandmarkType landmarkType);
}
