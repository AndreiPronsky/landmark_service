package org.pronsky.landmark_service.data.repository;

import org.pronsky.landmark_service.data.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides data access methods for services.
 */
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
