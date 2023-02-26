package com.dental.service;

import com.dental.domain.UserPayment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link UserPayment}.
 */
public interface UserPaymentService {
    /**
     * Save a userPayment.
     *
     * @param userPayment the entity to save.
     * @return the persisted entity.
     */
    UserPayment save(UserPayment userPayment);

    /**
     * Updates a userPayment.
     *
     * @param userPayment the entity to update.
     * @return the persisted entity.
     */
    UserPayment update(UserPayment userPayment);

    /**
     * Partially updates a userPayment.
     *
     * @param userPayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserPayment> partialUpdate(UserPayment userPayment);

    /**
     * Get all the userPayments.
     *
     * @return the list of entities.
     */
    List<UserPayment> findAll();

    /**
     * Get the "id" userPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserPayment> findOne(Long id);

    /**
     * Delete the "id" userPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
