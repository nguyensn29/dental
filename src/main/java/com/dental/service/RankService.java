package com.dental.service;

import com.dental.domain.Rank;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Rank}.
 */
public interface RankService {
    /**
     * Save a rank.
     *
     * @param rank the entity to save.
     * @return the persisted entity.
     */
    Rank save(Rank rank);

    /**
     * Updates a rank.
     *
     * @param rank the entity to update.
     * @return the persisted entity.
     */
    Rank update(Rank rank);

    /**
     * Partially updates a rank.
     *
     * @param rank the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Rank> partialUpdate(Rank rank);

    /**
     * Get all the ranks.
     *
     * @return the list of entities.
     */
    List<Rank> findAll();

    /**
     * Get the "id" rank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Rank> findOne(Long id);

    /**
     * Delete the "id" rank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
