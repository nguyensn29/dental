package com.dental.service;

import com.dental.domain.Combo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Combo}.
 */
public interface ComboService {
    /**
     * Save a combo.
     *
     * @param combo the entity to save.
     * @return the persisted entity.
     */
    Combo save(Combo combo);

    /**
     * Updates a combo.
     *
     * @param combo the entity to update.
     * @return the persisted entity.
     */
    Combo update(Combo combo);

    /**
     * Partially updates a combo.
     *
     * @param combo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Combo> partialUpdate(Combo combo);

    /**
     * Get all the combos.
     *
     * @return the list of entities.
     */
    List<Combo> findAll();

    /**
     * Get the "id" combo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Combo> findOne(Long id);

    /**
     * Delete the "id" combo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
