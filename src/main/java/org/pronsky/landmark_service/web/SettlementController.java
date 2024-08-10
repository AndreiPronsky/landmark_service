package org.pronsky.landmark_service.web;

import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Provides REST endpoints for managing settlements.
 */
public interface SettlementController {

    /**
     * Creates a new settlement based on the provided data transfer object.
     *
     * @param settlement the data transfer object containing the settlement data
     * @return the created settlement
     */
    @PostMapping
    ResponseEntity<SettlementDto> doPost(@RequestBody SettlementDto settlement);

    /**
     * Updates an existing settlement based on the provided data transfer object.
     *
     * @param settlement the data transfer object containing the updated settlement data
     * @return the updated settlement
     */
    @PatchMapping
    ResponseEntity<SettlementDto> doPatch(@RequestBody SettlementDto settlement);
}
