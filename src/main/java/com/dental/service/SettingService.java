package com.dental.service;

import com.dental.domain.Setting;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Setting}.
 */
public interface SettingService {
    /**
     * Save a setting.
     *
     * @param setting the entity to save.
     * @return the persisted entity.
     */
    Setting save(Setting setting);

    /**
     * Updates a setting.
     *
     * @param setting the entity to update.
     * @return the persisted entity.
     */
    Setting update(Setting setting);

    /**
     * Partially updates a setting.
     *
     * @param setting the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Setting> partialUpdate(Setting setting);

    /**
     * Get all the settings.
     *
     * @return the list of entities.
     */
    List<Setting> findAll();

    /**
     * Get the "id" setting.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Setting> findOne(Long id);

    /**
     * Delete the "id" setting.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
