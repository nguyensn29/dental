package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.Variant;
import com.dental.repository.VariantRepository;
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
 * Integration tests for the {@link VariantResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VariantResourceIT {

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final Long DEFAULT_SHOPIFY_ID = 1L;
    private static final Long UPDATED_SHOPIFY_ID = 2L;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final String DEFAULT_OPTION_1 = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_2 = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_3 = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_3 = "BBBBBBBBBB";

    private static final Long DEFAULT_IMAGE_ID = 1L;
    private static final Long UPDATED_IMAGE_ID = 2L;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Double DEFAULT_COMPARE_AT_PRICE = 1D;
    private static final Double UPDATED_COMPARE_AT_PRICE = 2D;

    private static final String ENTITY_API_URL = "/api/variants";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVariantMockMvc;

    private Variant variant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Variant createEntity(EntityManager em) {
        Variant variant = new Variant()
            .productId(DEFAULT_PRODUCT_ID)
            .shopifyId(DEFAULT_SHOPIFY_ID)
            .title(DEFAULT_TITLE)
            .price(DEFAULT_PRICE)
            .discount(DEFAULT_DISCOUNT)
            .option1(DEFAULT_OPTION_1)
            .option2(DEFAULT_OPTION_2)
            .option3(DEFAULT_OPTION_3)
            .imageId(DEFAULT_IMAGE_ID)
            .weight(DEFAULT_WEIGHT)
            .compareAtPrice(DEFAULT_COMPARE_AT_PRICE);
        return variant;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Variant createUpdatedEntity(EntityManager em) {
        Variant variant = new Variant()
            .productId(UPDATED_PRODUCT_ID)
            .shopifyId(UPDATED_SHOPIFY_ID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .option1(UPDATED_OPTION_1)
            .option2(UPDATED_OPTION_2)
            .option3(UPDATED_OPTION_3)
            .imageId(UPDATED_IMAGE_ID)
            .weight(UPDATED_WEIGHT)
            .compareAtPrice(UPDATED_COMPARE_AT_PRICE);
        return variant;
    }

    @BeforeEach
    public void initTest() {
        variant = createEntity(em);
    }

    @Test
    @Transactional
    void createVariant() throws Exception {
        int databaseSizeBeforeCreate = variantRepository.findAll().size();
        // Create the Variant
        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isCreated());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeCreate + 1);
        Variant testVariant = variantList.get(variantList.size() - 1);
        assertThat(testVariant.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testVariant.getShopifyId()).isEqualTo(DEFAULT_SHOPIFY_ID);
        assertThat(testVariant.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVariant.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testVariant.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testVariant.getOption1()).isEqualTo(DEFAULT_OPTION_1);
        assertThat(testVariant.getOption2()).isEqualTo(DEFAULT_OPTION_2);
        assertThat(testVariant.getOption3()).isEqualTo(DEFAULT_OPTION_3);
        assertThat(testVariant.getImageId()).isEqualTo(DEFAULT_IMAGE_ID);
        assertThat(testVariant.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testVariant.getCompareAtPrice()).isEqualTo(DEFAULT_COMPARE_AT_PRICE);
    }

    @Test
    @Transactional
    void createVariantWithExistingId() throws Exception {
        // Create the Variant with an existing ID
        variant.setId(1L);

        int databaseSizeBeforeCreate = variantRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isBadRequest());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = variantRepository.findAll().size();
        // set the field null
        variant.setProductId(null);

        // Create the Variant, which fails.

        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isBadRequest());

        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = variantRepository.findAll().size();
        // set the field null
        variant.setTitle(null);

        // Create the Variant, which fails.

        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isBadRequest());

        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = variantRepository.findAll().size();
        // set the field null
        variant.setPrice(null);

        // Create the Variant, which fails.

        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isBadRequest());

        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = variantRepository.findAll().size();
        // set the field null
        variant.setDiscount(null);

        // Create the Variant, which fails.

        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isBadRequest());

        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = variantRepository.findAll().size();
        // set the field null
        variant.setWeight(null);

        // Create the Variant, which fails.

        restVariantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isBadRequest());

        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVariants() throws Exception {
        // Initialize the database
        variantRepository.saveAndFlush(variant);

        // Get all the variantList
        restVariantMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variant.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].shopifyId").value(hasItem(DEFAULT_SHOPIFY_ID.intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].option1").value(hasItem(DEFAULT_OPTION_1)))
            .andExpect(jsonPath("$.[*].option2").value(hasItem(DEFAULT_OPTION_2)))
            .andExpect(jsonPath("$.[*].option3").value(hasItem(DEFAULT_OPTION_3)))
            .andExpect(jsonPath("$.[*].imageId").value(hasItem(DEFAULT_IMAGE_ID.intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].compareAtPrice").value(hasItem(DEFAULT_COMPARE_AT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    void getVariant() throws Exception {
        // Initialize the database
        variantRepository.saveAndFlush(variant);

        // Get the variant
        restVariantMockMvc
            .perform(get(ENTITY_API_URL_ID, variant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(variant.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.shopifyId").value(DEFAULT_SHOPIFY_ID.intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.option1").value(DEFAULT_OPTION_1))
            .andExpect(jsonPath("$.option2").value(DEFAULT_OPTION_2))
            .andExpect(jsonPath("$.option3").value(DEFAULT_OPTION_3))
            .andExpect(jsonPath("$.imageId").value(DEFAULT_IMAGE_ID.intValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.compareAtPrice").value(DEFAULT_COMPARE_AT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingVariant() throws Exception {
        // Get the variant
        restVariantMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVariant() throws Exception {
        // Initialize the database
        variantRepository.saveAndFlush(variant);

        int databaseSizeBeforeUpdate = variantRepository.findAll().size();

        // Update the variant
        Variant updatedVariant = variantRepository.findById(variant.getId()).get();
        // Disconnect from session so that the updates on updatedVariant are not directly saved in db
        em.detach(updatedVariant);
        updatedVariant
            .productId(UPDATED_PRODUCT_ID)
            .shopifyId(UPDATED_SHOPIFY_ID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .option1(UPDATED_OPTION_1)
            .option2(UPDATED_OPTION_2)
            .option3(UPDATED_OPTION_3)
            .imageId(UPDATED_IMAGE_ID)
            .weight(UPDATED_WEIGHT)
            .compareAtPrice(UPDATED_COMPARE_AT_PRICE);

        restVariantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVariant.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedVariant))
            )
            .andExpect(status().isOk());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
        Variant testVariant = variantList.get(variantList.size() - 1);
        assertThat(testVariant.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testVariant.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
        assertThat(testVariant.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVariant.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testVariant.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testVariant.getOption1()).isEqualTo(UPDATED_OPTION_1);
        assertThat(testVariant.getOption2()).isEqualTo(UPDATED_OPTION_2);
        assertThat(testVariant.getOption3()).isEqualTo(UPDATED_OPTION_3);
        assertThat(testVariant.getImageId()).isEqualTo(UPDATED_IMAGE_ID);
        assertThat(testVariant.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testVariant.getCompareAtPrice()).isEqualTo(UPDATED_COMPARE_AT_PRICE);
    }

    @Test
    @Transactional
    void putNonExistingVariant() throws Exception {
        int databaseSizeBeforeUpdate = variantRepository.findAll().size();
        variant.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVariantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, variant.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(variant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVariant() throws Exception {
        int databaseSizeBeforeUpdate = variantRepository.findAll().size();
        variant.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVariantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(variant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVariant() throws Exception {
        int databaseSizeBeforeUpdate = variantRepository.findAll().size();
        variant.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVariantMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVariantWithPatch() throws Exception {
        // Initialize the database
        variantRepository.saveAndFlush(variant);

        int databaseSizeBeforeUpdate = variantRepository.findAll().size();

        // Update the variant using partial update
        Variant partialUpdatedVariant = new Variant();
        partialUpdatedVariant.setId(variant.getId());

        partialUpdatedVariant
            .productId(UPDATED_PRODUCT_ID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .option2(UPDATED_OPTION_2)
            .option3(UPDATED_OPTION_3)
            .weight(UPDATED_WEIGHT)
            .compareAtPrice(UPDATED_COMPARE_AT_PRICE);

        restVariantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVariant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVariant))
            )
            .andExpect(status().isOk());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
        Variant testVariant = variantList.get(variantList.size() - 1);
        assertThat(testVariant.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testVariant.getShopifyId()).isEqualTo(DEFAULT_SHOPIFY_ID);
        assertThat(testVariant.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVariant.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testVariant.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testVariant.getOption1()).isEqualTo(DEFAULT_OPTION_1);
        assertThat(testVariant.getOption2()).isEqualTo(UPDATED_OPTION_2);
        assertThat(testVariant.getOption3()).isEqualTo(UPDATED_OPTION_3);
        assertThat(testVariant.getImageId()).isEqualTo(DEFAULT_IMAGE_ID);
        assertThat(testVariant.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testVariant.getCompareAtPrice()).isEqualTo(UPDATED_COMPARE_AT_PRICE);
    }

    @Test
    @Transactional
    void fullUpdateVariantWithPatch() throws Exception {
        // Initialize the database
        variantRepository.saveAndFlush(variant);

        int databaseSizeBeforeUpdate = variantRepository.findAll().size();

        // Update the variant using partial update
        Variant partialUpdatedVariant = new Variant();
        partialUpdatedVariant.setId(variant.getId());

        partialUpdatedVariant
            .productId(UPDATED_PRODUCT_ID)
            .shopifyId(UPDATED_SHOPIFY_ID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .option1(UPDATED_OPTION_1)
            .option2(UPDATED_OPTION_2)
            .option3(UPDATED_OPTION_3)
            .imageId(UPDATED_IMAGE_ID)
            .weight(UPDATED_WEIGHT)
            .compareAtPrice(UPDATED_COMPARE_AT_PRICE);

        restVariantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVariant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVariant))
            )
            .andExpect(status().isOk());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
        Variant testVariant = variantList.get(variantList.size() - 1);
        assertThat(testVariant.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testVariant.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
        assertThat(testVariant.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVariant.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testVariant.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testVariant.getOption1()).isEqualTo(UPDATED_OPTION_1);
        assertThat(testVariant.getOption2()).isEqualTo(UPDATED_OPTION_2);
        assertThat(testVariant.getOption3()).isEqualTo(UPDATED_OPTION_3);
        assertThat(testVariant.getImageId()).isEqualTo(UPDATED_IMAGE_ID);
        assertThat(testVariant.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testVariant.getCompareAtPrice()).isEqualTo(UPDATED_COMPARE_AT_PRICE);
    }

    @Test
    @Transactional
    void patchNonExistingVariant() throws Exception {
        int databaseSizeBeforeUpdate = variantRepository.findAll().size();
        variant.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVariantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, variant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(variant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVariant() throws Exception {
        int databaseSizeBeforeUpdate = variantRepository.findAll().size();
        variant.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVariantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(variant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVariant() throws Exception {
        int databaseSizeBeforeUpdate = variantRepository.findAll().size();
        variant.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVariantMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(variant)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Variant in the database
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVariant() throws Exception {
        // Initialize the database
        variantRepository.saveAndFlush(variant);

        int databaseSizeBeforeDelete = variantRepository.findAll().size();

        // Delete the variant
        restVariantMockMvc
            .perform(delete(ENTITY_API_URL_ID, variant.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Variant> variantList = variantRepository.findAll();
        assertThat(variantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
