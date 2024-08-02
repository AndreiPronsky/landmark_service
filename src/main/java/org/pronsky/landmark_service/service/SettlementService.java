package org.pronsky.landmark_service.service;

import org.pronsky.landmark_service.service.dto.SettlementDto;

/**
 * Service interface for managing settlements. Provides methods to create and update settlements.
 */
public interface SettlementService {

    /**
     * Creates a new settlement using the provided data.
     *
     * @param settlementDto A {@link SettlementDto} object containing the data for the new settlement.
     * @return A {@link SettlementDto} object representing the newly created settlement.
     */
    SettlementDto create(SettlementDto settlementDto);

    /**
     * Updates an existing settlement with the data provided in the DTO.
     *
     * @param settlementDto A {@link SettlementDto} object containing the updated data for the settlement.
     * @return A {@link SettlementDto} object representing the updated settlement.
     */
    SettlementDto update(SettlementDto settlementDto);
}
