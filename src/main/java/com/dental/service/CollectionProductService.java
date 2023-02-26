package com.dental.service;

import com.dental.domain.CollectionProduct;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CollectionProduct}.
 */
public interface CollectionProductService {
    /**
     * Save a collectionProduct.
     *
     * @param collectionProduct the entity to save.
     * @return the persisted entity.
     */
    CollectionProduct save(CollectionProduct collectionProduct);

    /**
     * Updates a collectionProduct.
     *
     * @param collectionProduct the entity to update.
     * @return the persisted entity.
     */
    CollectionProduct update(CollectionProduct collectionProduct);

    /**
     * Partially updates a collectionProduct.
     *
     * @param collectionProduct the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CollectionProduct> partialUpdate(CollectionProduct collectionProduct);

    /**
     * Get all the collectionProducts.
     *
     * @return the list of entities.
     */
    List<CollectionProduct> findAll();

    /**
     * Get the "id" collectionProduct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CollectionProduct> findOne(Long id);

    /**
     * Delete the "id" collectionProduct.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
