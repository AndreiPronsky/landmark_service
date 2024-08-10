package org.pronsky.landmark_service.web;

import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provides REST endpoints for managing landmarks.
 */
public interface LandmarkController {

    /**
     * Retrieves a list of landmarks located in the specified settlement.
     *
     * @param settlement the settlement for which to retrieve landmarks
     * @return a list of landmarks located in the specified settlement
     */
    @GetMapping("/by_settlement")
    ResponseEntity<List<LandmarkFullDto>> getAllBySettlement(@RequestParam String settlement);

    /**
     * Retrieves a list of landmarks, sorted by the given parameter and filtered by type.
     *
     * @param sortingParam the parameter to sort the landmarks by
     * @param type         the type of landmarks to retrieve
     * @return a list of landmarks, sorted by the given parameter and filtered by type
     */
    @GetMapping
    ResponseEntity<List<LandmarkFullDto>> getAll(@RequestParam String sortingParam, @RequestParam String type);

    /**
     * Creates a new landmark based on the provided data transfer object.
     *
     * @param landmark the data transfer object containing the landmark data
     * @return the created landmark
     */
    @PostMapping
    ResponseEntity<LandmarkFullDto> doPost(@RequestBody LandmarkTrimmedDto landmark);

    /**
     * Updates an existing landmark based on the provided data transfer object.
     *
     * @param landmark the data transfer object containing the updated landmark data
     * @return the updated landmark
     */
    @PatchMapping
    ResponseEntity<LandmarkFullDto> doPatch(@RequestBody LandmarkTrimmedDto landmark);

    /**
     * Deletes a landmark with the specified ID.
     *
     * @param id the ID of the landmark to delete
     * @return the deleted landmark
     */
    @DeleteMapping
    ResponseEntity<LandmarkFullDto> doDelete(@RequestParam Long id);
}
