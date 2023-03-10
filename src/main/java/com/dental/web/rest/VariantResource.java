package com.dental.web.rest;

import com.dental.domain.Variant;
import com.dental.repository.VariantRepository;
import com.dental.service.VariantService;
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
 * REST controller for managing {@link com.dental.domain.Variant}.
 */
@RestController
@RequestMapping("/api")
public class VariantResource {

    private final Logger log = LoggerFactory.getLogger(VariantResource.class);

    private static final String ENTITY_NAME = "variant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VariantService variantService;

    private final VariantRepository variantRepository;

    public VariantResource(VariantService variantService, VariantRepository variantRepository) {
        this.variantService = variantService;
        this.variantRepository = variantRepository;
    }

    /**
     * {@code POST  /variants} : Create a new variant.
     *
     * @param variant the variant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new variant, or with status {@code 400 (Bad Request)} if the variant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/variants")
    public ResponseEntity<Variant> createVariant(@Valid @RequestBody Variant variant) throws URISyntaxException {
        log.debug("REST request to save Variant : {}", variant);
        if (variant.getId() != null) {
            throw new BadRequestAlertException("A new variant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Variant result = variantService.save(variant);
        return ResponseEntity
            .created(new URI("/api/variants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /variants/:id} : Updates an existing variant.
     *
     * @param id the id of the variant to save.
     * @param variant the variant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variant,
     * or with status {@code 400 (Bad Request)} if the variant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the variant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/variants/{id}")
    public ResponseEntity<Variant> updateVariant(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Variant variant
    ) throws URISyntaxException {
        log.debug("REST request to update Variant : {}, {}", id, variant);
        if (variant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, variant.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!variantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Variant result = variantService.update(variant);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, variant.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /variants/:id} : Partial updates given fields of an existing variant, field will ignore if it is null
     *
     * @param id the id of the variant to save.
     * @param variant the variant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variant,
     * or with status {@code 400 (Bad Request)} if the variant is not valid,
     * or with status {@code 404 (Not Found)} if the variant is not found,
     * or with status {@code 500 (Internal Server Error)} if the variant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/variants/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Variant> partialUpdateVariant(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Variant variant
    ) throws URISyntaxException {
        log.debug("REST request to partial update Variant partially : {}, {}", id, variant);
        if (variant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, variant.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!variantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Variant> result = variantService.partialUpdate(variant);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, variant.getId().toString())
        );
    }

    /**
     * {@code GET  /variants} : get all the variants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variants in body.
     */
    @GetMapping("/variants")
    public List<Variant> getAllVariants() {
        log.debug("REST request to get all Variants");
        return variantService.findAll();
    }

    /**
     * {@code GET  /variants/:id} : get the "id" variant.
     *
     * @param id the id of the variant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the variant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/variants/{id}")
    public ResponseEntity<Variant> getVariant(@PathVariable Long id) {
        log.debug("REST request to get Variant : {}", id);
        Optional<Variant> variant = variantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(variant);
    }

    /**
     * {@code DELETE  /variants/:id} : delete the "id" variant.
     *
     * @param id the id of the variant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/variants/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        log.debug("REST request to delete Variant : {}", id);
        variantService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
