package com.dental.service;

import com.dental.domain.ProductUser;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ProductUser}.
 */
public interface ProductUserService {
    /**
     * Save a productUser.
     *
     * @param productUser the entity to save.
     * @return the persisted entity.
     */
    ProductUser save(ProductUser productUser);

    /**
     * Updates a productUser.
     *
     * @param productUser the entity to update.
     * @return the persisted entity.
     */
    ProductUser update(ProductUser productUser);

    /**
     * Partially updates a productUser.
     *
     * @param productUser the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProductUser> partialUpdate(ProductUser productUser);

    /**
     * Get all the productUsers.
     *
     * @return the list of entities.
     */
    List<ProductUser> findAll();

    /**
     * Get the "id" productUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductUser> findOne(Long id);

    /**
     * Delete the "id" productUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
