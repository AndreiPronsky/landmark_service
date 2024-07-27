package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {
}
