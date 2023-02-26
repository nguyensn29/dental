package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.CollectionProduct;
import com.dental.repository.CollectionProductRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CollectionProductResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CollectionProductResourceIT {

    private static final Long DEFAULT_COLLECTION_ID = 1L;
    private static final Long UPDATED_COLLECTION_ID = 2L;

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final String ENTITY_API_URL = "/api/collection-products";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CollectionProductRepository collectionProductRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCollectionProductMockMvc;

    private CollectionProduct collectionProduct;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionProduct createEntity(EntityManager em) {
        CollectionProduct collectionProduct = new CollectionProduct().collectionId(DEFAULT_COLLECTION_ID).productId(DEFAULT_PRODUCT_ID);
        return collectionProduct;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionProduct createUpdatedEntity(EntityManager em) {
        CollectionProduct collectionProduct = new CollectionProduct().collectionId(UPDATED_COLLECTION_ID).productId(UPDATED_PRODUCT_ID);
        return collectionProduct;
    }

    @BeforeEach
    public void initTest() {
        collectionProduct = createEntity(em);
    }

    @Test
    @Transactional
    void createCollectionProduct() throws Exception {
        int databaseSizeBeforeCreate = collectionProductRepository.findAll().size();
        // Create the CollectionProduct
        restCollectionProductMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isCreated());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeCreate + 1);
        CollectionProduct testCollectionProduct = collectionProductList.get(collectionProductList.size() - 1);
        assertThat(testCollectionProduct.getCollectionId()).isEqualTo(DEFAULT_COLLECTION_ID);
        assertThat(testCollectionProduct.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
    }

    @Test
    @Transactional
    void createCollectionProductWithExistingId() throws Exception {
        // Create the CollectionProduct with an existing ID
        collectionProduct.setId(1L);

        int databaseSizeBeforeCreate = collectionProductRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollectionProductMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCollectionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionProductRepository.findAll().size();
        // set the field null
        collectionProduct.setCollectionId(null);

        // Create the CollectionProduct, which fails.

        restCollectionProductMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionProductRepository.findAll().size();
        // set the field null
        collectionProduct.setProductId(null);

        // Create the CollectionProduct, which fails.

        restCollectionProductMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCollectionProducts() throws Exception {
        // Initialize the database
        collectionProductRepository.saveAndFlush(collectionProduct);

        // Get all the collectionProductList
        restCollectionProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(collectionProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].collectionId").value(hasItem(DEFAULT_COLLECTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())));
    }

    @Test
    @Transactional
    void getCollectionProduct() throws Exception {
        // Initialize the database
        collectionProductRepository.saveAndFlush(collectionProduct);

        // Get the collectionProduct
        restCollectionProductMockMvc
            .perform(get(ENTITY_API_URL_ID, collectionProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(collectionProduct.getId().intValue()))
            .andExpect(jsonPath("$.collectionId").value(DEFAULT_COLLECTION_ID.intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCollectionProduct() throws Exception {
        // Get the collectionProduct
        restCollectionProductMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCollectionProduct() throws Exception {
        // Initialize the database
        collectionProductRepository.saveAndFlush(collectionProduct);

        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();

        // Update the collectionProduct
        CollectionProduct updatedCollectionProduct = collectionProductRepository.findById(collectionProduct.getId()).get();
        // Disconnect from session so that the updates on updatedCollectionProduct are not directly saved in db
        em.detach(updatedCollectionProduct);
        updatedCollectionProduct.collectionId(UPDATED_COLLECTION_ID).productId(UPDATED_PRODUCT_ID);

        restCollectionProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCollectionProduct.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCollectionProduct))
            )
            .andExpect(status().isOk());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
        CollectionProduct testCollectionProduct = collectionProductList.get(collectionProductList.size() - 1);
        assertThat(testCollectionProduct.getCollectionId()).isEqualTo(UPDATED_COLLECTION_ID);
        assertThat(testCollectionProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
    }

    @Test
    @Transactional
    void putNonExistingCollectionProduct() throws Exception {
        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();
        collectionProduct.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollectionProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, collectionProduct.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCollectionProduct() throws Exception {
        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();
        collectionProduct.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollectionProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCollectionProduct() throws Exception {
        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();
        collectionProduct.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollectionProductMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCollectionProductWithPatch() throws Exception {
        // Initialize the database
        collectionProductRepository.saveAndFlush(collectionProduct);

        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();

        // Update the collectionProduct using partial update
        CollectionProduct partialUpdatedCollectionProduct = new CollectionProduct();
        partialUpdatedCollectionProduct.setId(collectionProduct.getId());

        partialUpdatedCollectionProduct.collectionId(UPDATED_COLLECTION_ID).productId(UPDATED_PRODUCT_ID);

        restCollectionProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCollectionProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCollectionProduct))
            )
            .andExpect(status().isOk());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
        CollectionProduct testCollectionProduct = collectionProductList.get(collectionProductList.size() - 1);
        assertThat(testCollectionProduct.getCollectionId()).isEqualTo(UPDATED_COLLECTION_ID);
        assertThat(testCollectionProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
    }

    @Test
    @Transactional
    void fullUpdateCollectionProductWithPatch() throws Exception {
        // Initialize the database
        collectionProductRepository.saveAndFlush(collectionProduct);

        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();

        // Update the collectionProduct using partial update
        CollectionProduct partialUpdatedCollectionProduct = new CollectionProduct();
        partialUpdatedCollectionProduct.setId(collectionProduct.getId());

        partialUpdatedCollectionProduct.collectionId(UPDATED_COLLECTION_ID).productId(UPDATED_PRODUCT_ID);

        restCollectionProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCollectionProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCollectionProduct))
            )
            .andExpect(status().isOk());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
        CollectionProduct testCollectionProduct = collectionProductList.get(collectionProductList.size() - 1);
        assertThat(testCollectionProduct.getCollectionId()).isEqualTo(UPDATED_COLLECTION_ID);
        assertThat(testCollectionProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingCollectionProduct() throws Exception {
        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();
        collectionProduct.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollectionProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, collectionProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCollectionProduct() throws Exception {
        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();
        collectionProduct.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollectionProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCollectionProduct() throws Exception {
        int databaseSizeBeforeUpdate = collectionProductRepository.findAll().size();
        collectionProduct.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollectionProductMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(collectionProduct))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CollectionProduct in the database
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCollectionProduct() throws Exception {
        // Initialize the database
        collectionProductRepository.saveAndFlush(collectionProduct);

        int databaseSizeBeforeDelete = collectionProductRepository.findAll().size();

        // Delete the collectionProduct
        restCollectionProductMockMvc
            .perform(delete(ENTITY_API_URL_ID, collectionProduct.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CollectionProduct> collectionProductList = collectionProductRepository.findAll();
        assertThat(collectionProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
