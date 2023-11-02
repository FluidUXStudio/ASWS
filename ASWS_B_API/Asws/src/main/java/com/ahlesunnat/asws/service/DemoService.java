package com.ahlesunnat.asws.service;

import com.ahlesunnat.asws.domain.Demo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Demo}.
 */
public interface DemoService {
    /**
     * Save a demo.
     *
     * @param demo the entity to save.
     * @return the persisted entity.
     */
    Demo save(Demo demo);

    /**
     * Updates a demo.
     *
     * @param demo the entity to update.
     * @return the persisted entity.
     */
    Demo update(Demo demo);

    /**
     * Partially updates a demo.
     *
     * @param demo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Demo> partialUpdate(Demo demo);

    /**
     * Get all the demos.
     *
     * @return the list of entities.
     */
    List<Demo> findAll();

    /**
     * Get the "id" demo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Demo> findOne(Long id);

    /**
     * Delete the "id" demo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
