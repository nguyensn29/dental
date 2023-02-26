package com.dental.web.rest;

import com.dental.domain.CollectionProduct;
import com.dental.repository.CollectionProductRepository;
import com.dental.service.CollectionProductService;
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
 * REST controller for managing {@link com.dental.domain.CollectionProduct}.
 */
@RestController
@RequestMapping("/api")
public class CollectionProductResource {

    private final Logger log = LoggerFactory.getLogger(CollectionProductResource.class);

    private static final String ENTITY_NAME = "collectionProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollectionProductService collectionProductService;

    private final CollectionProductRepository collectionProductRepository;

    public CollectionProductResource(
        CollectionProductService collectionProductService,
        CollectionProductRepository collectionProductRepository
    ) {
        this.collectionProductService = collectionProductService;
        this.collectionProductRepository = collectionProductRepository;
    }

    /**
     * {@code POST  /collection-products} : Create a new collectionProduct.
     *
     * @param collectionProduct the collectionProduct to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collectionProduct, or with status {@code 400 (Bad Request)} if the collectionProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/collection-products")
    public ResponseEntity<CollectionProduct> createCollectionProduct(@Valid @RequestBody CollectionProduct collectionProduct)
        throws URISyntaxException {
        log.debug("REST request to save CollectionProduct : {}", collectionProduct);
        if (collectionProduct.getId() != null) {
            throw new BadRequestAlertException("A new collectionProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollectionProduct result = collectionProductService.save(collectionProduct);
        return ResponseEntity
            .created(new URI("/api/collection-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /collection-products/:id} : Updates an existing collectionProduct.
     *
     * @param id the id of the collectionProduct to save.
     * @param collectionProduct the collectionProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionProduct,
     * or with status {@code 400 (Bad Request)} if the collectionProduct is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collectionProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/collection-products/{id}")
    public ResponseEntity<CollectionProduct> updateCollectionProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CollectionProduct collectionProduct
    ) throws URISyntaxException {
        log.debug("REST request to update CollectionProduct : {}, {}", id, collectionProduct);
        if (collectionProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, collectionProduct.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!collectionProductRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CollectionProduct result = collectionProductService.update(collectionProduct);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collectionProduct.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /collection-products/:id} : Partial updates given fields of an existing collectionProduct, field will ignore if it is null
     *
     * @param id the id of the collectionProduct to save.
     * @param collectionProduct the collectionProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionProduct,
     * or with status {@code 400 (Bad Request)} if the collectionProduct is not valid,
     * or with status {@code 404 (Not Found)} if the collectionProduct is not found,
     * or with status {@code 500 (Internal Server Error)} if the collectionProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/collection-products/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CollectionProduct> partialUpdateCollectionProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CollectionProduct collectionProduct
    ) throws URISyntaxException {
        log.debug("REST request to partial update CollectionProduct partially : {}, {}", id, collectionProduct);
        if (collectionProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, collectionProduct.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!collectionProductRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CollectionProduct> result = collectionProductService.partialUpdate(collectionProduct);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collectionProduct.getId().toString())
        );
    }

    /**
     * {@code GET  /collection-products} : get all the collectionProducts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of collectionProducts in body.
     */
    @GetMapping("/collection-products")
    public List<CollectionProduct> getAllCollectionProducts() {
        log.debug("REST request to get all CollectionProducts");
        return collectionProductService.findAll();
    }

    /**
     * {@code GET  /collection-products/:id} : get the "id" collectionProduct.
     *
     * @param id the id of the collectionProduct to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collectionProduct, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/collection-products/{id}")
    public ResponseEntity<CollectionProduct> getCollectionProduct(@PathVariable Long id) {
        log.debug("REST request to get CollectionProduct : {}", id);
        Optional<CollectionProduct> collectionProduct = collectionProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collectionProduct);
    }

    /**
     * {@code DELETE  /collection-products/:id} : delete the "id" collectionProduct.
     *
     * @param id the id of the collectionProduct to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/collection-products/{id}")
    public ResponseEntity<Void> deleteCollectionProduct(@PathVariable Long id) {
        log.debug("REST request to delete CollectionProduct : {}", id);
        collectionProductService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
