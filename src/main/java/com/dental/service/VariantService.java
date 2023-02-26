package com.dental.service;

import com.dental.domain.Variant;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Variant}.
 */
public interface VariantService {
    /**
     * Save a variant.
     *
     * @param variant the entity to save.
     * @return the persisted entity.
     */
    Variant save(Variant variant);

    /**
     * Updates a variant.
     *
     * @param variant the entity to update.
     * @return the persisted entity.
     */
    Variant update(Variant variant);

    /**
     * Partially updates a variant.
     *
     * @param variant the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Variant> partialUpdate(Variant variant);

    /**
     * Get all the variants.
     *
     * @return the list of entities.
     */
    List<Variant> findAll();

    /**
     * Get the "id" variant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Variant> findOne(Long id);

    /**
     * Delete the "id" variant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
