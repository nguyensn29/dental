package com.dental.service;

import com.dental.domain.OrderDetails;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link OrderDetails}.
 */
public interface OrderDetailsService {
    /**
     * Save a orderDetails.
     *
     * @param orderDetails the entity to save.
     * @return the persisted entity.
     */
    OrderDetails save(OrderDetails orderDetails);

    /**
     * Updates a orderDetails.
     *
     * @param orderDetails the entity to update.
     * @return the persisted entity.
     */
    OrderDetails update(OrderDetails orderDetails);

    /**
     * Partially updates a orderDetails.
     *
     * @param orderDetails the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderDetails> partialUpdate(OrderDetails orderDetails);

    /**
     * Get all the orderDetails.
     *
     * @return the list of entities.
     */
    List<OrderDetails> findAll();

    /**
     * Get the "id" orderDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderDetails> findOne(Long id);

    /**
     * Delete the "id" orderDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
