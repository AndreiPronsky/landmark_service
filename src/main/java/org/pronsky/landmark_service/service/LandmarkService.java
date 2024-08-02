package org.pronsky.landmark_service.service;

import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;

import java.util.List;

/**
 * Service interface for managing landmarks. Provides methods to retrieve, create, update, and delete landmarks
 * based on various criteria.
 */
public interface LandmarkService {

    /**
     * Retrieves a list of all landmarks of a specific type, sorted by a given parameter.
     *
     * @param landmarkType The type of landmarks to retrieve (e.g., "Historical", "Architecture").
     * @param sortingParam The parameter by which to sort the landmarks (e.g., "name", "id").
     * @return A list of {@link LandmarkFullDto} objects representing the landmarks, sorted according to the specified parameter.
     */
    List<LandmarkFullDto> getAllByTypeSorted(String landmarkType, String sortingParam);

    /**
     * Retrieves a list of all landmarks located in a specific settlement.
     *
     * @param settlement The name of the settlement where the landmarks are located.
     * @return A list of {@link LandmarkFullDto} objects representing the landmarks in the specified settlement.
     */
    List<LandmarkFullDto> getAllBySettlement(String settlement);

    /**
     * Creates a new landmark using the provided data.
     *
     * @param dto A {@link LandmarkTrimmedDto} object containing the data for the new landmark.
     * @return A {@link LandmarkFullDto} object representing the newly created landmark.
     */
    LandmarkFullDto create(LandmarkTrimmedDto dto);

    /**
     * Updates an existing landmark with the data provided in the DTO.
     *
     * @param dto A {@link LandmarkTrimmedDto} object containing the updated data for the landmark.
     * @return A {@link LandmarkFullDto} object representing the updated landmark.
     */
    LandmarkFullDto update(LandmarkTrimmedDto dto);

    /**
     * Deletes a landmark with the specified ID.
     *
     * @param id The ID of the landmark to delete.
     */
    void delete(Long id);
}
