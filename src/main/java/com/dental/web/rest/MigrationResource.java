package com.dental.web.rest;

import com.dental.domain.Migration;
import com.dental.repository.MigrationRepository;
import com.dental.service.MigrationService;
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
 * REST controller for managing {@link com.dental.domain.Migration}.
 */
@RestController
@RequestMapping("/api")
public class MigrationResource {

    private final Logger log = LoggerFactory.getLogger(MigrationResource.class);

    private static final String ENTITY_NAME = "migration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MigrationService migrationService;

    private final MigrationRepository migrationRepository;

    public MigrationResource(MigrationService migrationService, MigrationRepository migrationRepository) {
        this.migrationService = migrationService;
        this.migrationRepository = migrationRepository;
    }

    /**
     * {@code POST  /migrations} : Create a new migration.
     *
     * @param migration the migration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new migration, or with status {@code 400 (Bad Request)} if the migration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/migrations")
    public ResponseEntity<Migration> createMigration(@Valid @RequestBody Migration migration) throws URISyntaxException {
        log.debug("REST request to save Migration : {}", migration);
        if (migration.getId() != null) {
            throw new BadRequestAlertException("A new migration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Migration result = migrationService.save(migration);
        return ResponseEntity
            .created(new URI("/api/migrations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /migrations/:id} : Updates an existing migration.
     *
     * @param id the id of the migration to save.
     * @param migration the migration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated migration,
     * or with status {@code 400 (Bad Request)} if the migration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the migration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/migrations/{id}")
    public ResponseEntity<Migration> updateMigration(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Migration migration
    ) throws URISyntaxException {
        log.debug("REST request to update Migration : {}, {}", id, migration);
        if (migration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, migration.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!migrationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Migration result = migrationService.update(migration);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, migration.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /migrations/:id} : Partial updates given fields of an existing migration, field will ignore if it is null
     *
     * @param id the id of the migration to save.
     * @param migration the migration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated migration,
     * or with status {@code 400 (Bad Request)} if the migration is not valid,
     * or with status {@code 404 (Not Found)} if the migration is not found,
     * or with status {@code 500 (Internal Server Error)} if the migration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/migrations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Migration> partialUpdateMigration(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Migration migration
    ) throws URISyntaxException {
        log.debug("REST request to partial update Migration partially : {}, {}", id, migration);
        if (migration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, migration.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!migrationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Migration> result = migrationService.partialUpdate(migration);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, migration.getId().toString())
        );
    }

    /**
     * {@code GET  /migrations} : get all the migrations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of migrations in body.
     */
    @GetMapping("/migrations")
    public List<Migration> getAllMigrations() {
        log.debug("REST request to get all Migrations");
        return migrationService.findAll();
    }

    /**
     * {@code GET  /migrations/:id} : get the "id" migration.
     *
     * @param id the id of the migration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the migration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/migrations/{id}")
    public ResponseEntity<Migration> getMigration(@PathVariable Long id) {
        log.debug("REST request to get Migration : {}", id);
        Optional<Migration> migration = migrationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(migration);
    }

    /**
     * {@code DELETE  /migrations/:id} : delete the "id" migration.
     *
     * @param id the id of the migration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/migrations/{id}")
    public ResponseEntity<Void> deleteMigration(@PathVariable Long id) {
        log.debug("REST request to delete Migration : {}", id);
        migrationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
