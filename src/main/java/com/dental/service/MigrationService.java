package com.dental.service;

import com.dental.domain.Migration;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Migration}.
 */
public interface MigrationService {
    /**
     * Save a migration.
     *
     * @param migration the entity to save.
     * @return the persisted entity.
     */
    Migration save(Migration migration);

    /**
     * Updates a migration.
     *
     * @param migration the entity to update.
     * @return the persisted entity.
     */
    Migration update(Migration migration);

    /**
     * Partially updates a migration.
     *
     * @param migration the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Migration> partialUpdate(Migration migration);

    /**
     * Get all the migrations.
     *
     * @return the list of entities.
     */
    List<Migration> findAll();

    /**
     * Get the "id" migration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Migration> findOne(Long id);

    /**
     * Delete the "id" migration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
