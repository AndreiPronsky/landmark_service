package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides data access methods for settlements.
 */
public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    /**
     * Retrieves a settlement by its name.
     *
     * @param name the name of the settlement to retrieve
     * @return the settlement with the specified name, or null if not found
     */
    Settlement findByName(String name);
}
