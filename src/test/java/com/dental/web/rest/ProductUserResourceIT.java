package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.ProductUser;
import com.dental.repository.ProductUserRepository;
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
 * Integration tests for the {@link ProductUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductUserResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final Integer DEFAULT_PRODUCT_TYPE = 1;
    private static final Integer UPDATED_PRODUCT_TYPE = 2;

    private static final String ENTITY_API_URL = "/api/product-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductUserRepository productUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductUserMockMvc;

    private ProductUser productUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductUser createEntity(EntityManager em) {
        ProductUser productUser = new ProductUser().userId(DEFAULT_USER_ID).productId(DEFAULT_PRODUCT_ID).productType(DEFAULT_PRODUCT_TYPE);
        return productUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductUser createUpdatedEntity(EntityManager em) {
        ProductUser productUser = new ProductUser().userId(UPDATED_USER_ID).productId(UPDATED_PRODUCT_ID).productType(UPDATED_PRODUCT_TYPE);
        return productUser;
    }

    @BeforeEach
    public void initTest() {
        productUser = createEntity(em);
    }

    @Test
    @Transactional
    void createProductUser() throws Exception {
        int databaseSizeBeforeCreate = productUserRepository.findAll().size();
        // Create the ProductUser
        restProductUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productUser)))
            .andExpect(status().isCreated());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeCreate + 1);
        ProductUser testProductUser = productUserList.get(productUserList.size() - 1);
        assertThat(testProductUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProductUser.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProductUser.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void createProductUserWithExistingId() throws Exception {
        // Create the ProductUser with an existing ID
        productUser.setId(1L);

        int databaseSizeBeforeCreate = productUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productUser)))
            .andExpect(status().isBadRequest());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productUserRepository.findAll().size();
        // set the field null
        productUser.setUserId(null);

        // Create the ProductUser, which fails.

        restProductUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productUser)))
            .andExpect(status().isBadRequest());

        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productUserRepository.findAll().size();
        // set the field null
        productUser.setProductId(null);

        // Create the ProductUser, which fails.

        restProductUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productUser)))
            .andExpect(status().isBadRequest());

        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productUserRepository.findAll().size();
        // set the field null
        productUser.setProductType(null);

        // Create the ProductUser, which fails.

        restProductUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productUser)))
            .andExpect(status().isBadRequest());

        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProductUsers() throws Exception {
        // Initialize the database
        productUserRepository.saveAndFlush(productUser);

        // Get all the productUserList
        restProductUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE)));
    }

    @Test
    @Transactional
    void getProductUser() throws Exception {
        // Initialize the database
        productUserRepository.saveAndFlush(productUser);

        // Get the productUser
        restProductUserMockMvc
            .perform(get(ENTITY_API_URL_ID, productUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productUser.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingProductUser() throws Exception {
        // Get the productUser
        restProductUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProductUser() throws Exception {
        // Initialize the database
        productUserRepository.saveAndFlush(productUser);

        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();

        // Update the productUser
        ProductUser updatedProductUser = productUserRepository.findById(productUser.getId()).get();
        // Disconnect from session so that the updates on updatedProductUser are not directly saved in db
        em.detach(updatedProductUser);
        updatedProductUser.userId(UPDATED_USER_ID).productId(UPDATED_PRODUCT_ID).productType(UPDATED_PRODUCT_TYPE);

        restProductUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProductUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProductUser))
            )
            .andExpect(status().isOk());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
        ProductUser testProductUser = productUserList.get(productUserList.size() - 1);
        assertThat(testProductUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProductUser.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProductUser.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingProductUser() throws Exception {
        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();
        productUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductUser() throws Exception {
        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();
        productUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductUser() throws Exception {
        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();
        productUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductUserWithPatch() throws Exception {
        // Initialize the database
        productUserRepository.saveAndFlush(productUser);

        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();

        // Update the productUser using partial update
        ProductUser partialUpdatedProductUser = new ProductUser();
        partialUpdatedProductUser.setId(productUser.getId());

        partialUpdatedProductUser.userId(UPDATED_USER_ID);

        restProductUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductUser))
            )
            .andExpect(status().isOk());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
        ProductUser testProductUser = productUserList.get(productUserList.size() - 1);
        assertThat(testProductUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProductUser.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProductUser.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateProductUserWithPatch() throws Exception {
        // Initialize the database
        productUserRepository.saveAndFlush(productUser);

        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();

        // Update the productUser using partial update
        ProductUser partialUpdatedProductUser = new ProductUser();
        partialUpdatedProductUser.setId(productUser.getId());

        partialUpdatedProductUser.userId(UPDATED_USER_ID).productId(UPDATED_PRODUCT_ID).productType(UPDATED_PRODUCT_TYPE);

        restProductUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductUser))
            )
            .andExpect(status().isOk());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
        ProductUser testProductUser = productUserList.get(productUserList.size() - 1);
        assertThat(testProductUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProductUser.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProductUser.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingProductUser() throws Exception {
        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();
        productUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductUser() throws Exception {
        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();
        productUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductUser() throws Exception {
        int databaseSizeBeforeUpdate = productUserRepository.findAll().size();
        productUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(productUser))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductUser in the database
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductUser() throws Exception {
        // Initialize the database
        productUserRepository.saveAndFlush(productUser);

        int databaseSizeBeforeDelete = productUserRepository.findAll().size();

        // Delete the productUser
        restProductUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, productUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductUser> productUserList = productUserRepository.findAll();
        assertThat(productUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
