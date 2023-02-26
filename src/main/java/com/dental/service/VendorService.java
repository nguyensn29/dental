package com.dental.service;

import com.dental.domain.Vendor;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Vendor}.
 */
public interface VendorService {
    /**
     * Save a vendor.
     *
     * @param vendor the entity to save.
     * @return the persisted entity.
     */
    Vendor save(Vendor vendor);

    /**
     * Updates a vendor.
     *
     * @param vendor the entity to update.
     * @return the persisted entity.
     */
    Vendor update(Vendor vendor);

    /**
     * Partially updates a vendor.
     *
     * @param vendor the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Vendor> partialUpdate(Vendor vendor);

    /**
     * Get all the vendors.
     *
     * @return the list of entities.
     */
    List<Vendor> findAll();

    /**
     * Get the "id" vendor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Vendor> findOne(Long id);

    /**
     * Delete the "id" vendor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
