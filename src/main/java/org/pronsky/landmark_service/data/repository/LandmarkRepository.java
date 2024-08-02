package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Provides data access methods for landmarks.
 */
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    /**
     * Retrieves a list of landmarks located in the specified settlement.
     *
     * @param settlementName the name of the settlement for which to retrieve landmarks
     * @return a list of landmarks located in the specified settlement
     */
    List<Landmark> findAllBySettlementName(String settlementName);

    /**
     * Retrieves a list of landmarks of the specified type.
     *
     * @param landmarkType the type of landmarks to retrieve
     * @return a list of landmarks of the specified type
     */
    List<Landmark> findAllByType(Landmark.LandmarkType landmarkType);
}
