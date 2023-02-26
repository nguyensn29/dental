package com.dental.web.rest;

import com.dental.domain.Combo;
import com.dental.repository.ComboRepository;
import com.dental.service.ComboService;
import com.dental.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dental.domain.Combo}.
 */
@RestController
@RequestMapping("/api")
public class ComboResource {

    private final Logger log = LoggerFactory.getLogger(ComboResource.class);

    private static final String ENTITY_NAME = "combo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComboService comboService;

    private final ComboRepository comboRepository;

    public ComboResource(ComboService comboService, ComboRepository comboRepository) {
        this.comboService = comboService;
        this.comboRepository = comboRepository;
    }

    /**
     * {@code POST  /combos} : Create a new combo.
     *
     * @param combo the combo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new combo, or with status {@code 400 (Bad Request)} if the combo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/combos")
    public ResponseEntity<Combo> createCombo(@Valid @RequestBody Combo combo) throws URISyntaxException {
        log.debug("REST request to save Combo : {}", combo);
        if (combo.getId() != null) {
            throw new BadRequestAlertException("A new combo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Combo result = comboService.save(combo);
        return ResponseEntity
            .created(new URI("/api/combos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /combos/:id} : Updates an existing combo.
     *
     * @param id the id of the combo to save.
     * @param combo the combo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated combo,
     * or with status {@code 400 (Bad Request)} if the combo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the combo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/combos/{id}")
    public ResponseEntity<Combo> updateCombo(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Combo combo)
        throws URISyntaxException {
        log.debug("REST request to update Combo : {}, {}", id, combo);
        if (combo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, combo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comboRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Combo result = comboService.update(combo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, combo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /combos/:id} : Partial updates given fields of an existing combo, field will ignore if it is null
     *
     * @param id the id of the combo to save.
     * @param combo the combo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated combo,
     * or with status {@code 400 (Bad Request)} if the combo is not valid,
     * or with status {@code 404 (Not Found)} if the combo is not found,
     * or with status {@code 500 (Internal Server Error)} if the combo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/combos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Combo> partialUpdateCombo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Combo combo
    ) throws URISyntaxException {
        log.debug("REST request to partial update Combo partially : {}, {}", id, combo);
        if (combo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, combo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comboRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Combo> result = comboService.partialUpdate(combo);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, combo.getId().toString())
        );
    }

    /**
     * {@code GET  /combos} : get all the combos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of combos in body.
     */
    @GetMapping("/combos")
    public List<Combo> getAllCombos() {
        log.debug("REST request to get all Combos");
        return comboService.findAll();
    }

    /**
     * {@code GET  /combos/:id} : get the "id" combo.
     *
     * @param id the id of the combo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the combo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/combos/{id}")
    public ResponseEntity<Combo> getCombo(@PathVariable Long id) {
        log.debug("REST request to get Combo : {}", id);
        Optional<Combo> combo = comboService.findOne(id);
        return ResponseUtil.wrapOrNotFound(combo);
    }

    /**
     * {@code DELETE  /combos/:id} : delete the "id" combo.
     *
     * @param id the id of the combo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/combos/{id}")
    public ResponseEntity<Void> deleteCombo(@PathVariable Long id) {
        log.debug("REST request to delete Combo : {}", id);
        comboService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
