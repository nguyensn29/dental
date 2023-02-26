package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.Combo;
import com.dental.repository.ComboRepository;
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
 * Integration tests for the {@link ComboResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ComboResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LIKED = 1;
    private static final Integer UPDATED_LIKED = 2;

    private static final String ENTITY_API_URL = "/api/combos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComboMockMvc;

    private Combo combo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Combo createEntity(EntityManager em) {
        Combo combo = new Combo()
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE)
            .discount(DEFAULT_DISCOUNT)
            .weight(DEFAULT_WEIGHT)
            .point(DEFAULT_POINT)
            .description(DEFAULT_DESCRIPTION)
            .liked(DEFAULT_LIKED);
        return combo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Combo createUpdatedEntity(EntityManager em) {
        Combo combo = new Combo()
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .weight(UPDATED_WEIGHT)
            .point(UPDATED_POINT)
            .description(UPDATED_DESCRIPTION)
            .liked(UPDATED_LIKED);
        return combo;
    }

    @BeforeEach
    public void initTest() {
        combo = createEntity(em);
    }

    @Test
    @Transactional
    void createCombo() throws Exception {
        int databaseSizeBeforeCreate = comboRepository.findAll().size();
        // Create the Combo
        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isCreated());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeCreate + 1);
        Combo testCombo = comboList.get(comboList.size() - 1);
        assertThat(testCombo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCombo.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testCombo.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testCombo.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testCombo.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testCombo.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCombo.getLiked()).isEqualTo(DEFAULT_LIKED);
    }

    @Test
    @Transactional
    void createComboWithExistingId() throws Exception {
        // Create the Combo with an existing ID
        combo.setId(1L);

        int databaseSizeBeforeCreate = comboRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isBadRequest());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = comboRepository.findAll().size();
        // set the field null
        combo.setName(null);

        // Create the Combo, which fails.

        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isBadRequest());

        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = comboRepository.findAll().size();
        // set the field null
        combo.setPrice(null);

        // Create the Combo, which fails.

        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isBadRequest());

        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = comboRepository.findAll().size();
        // set the field null
        combo.setWeight(null);

        // Create the Combo, which fails.

        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isBadRequest());

        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = comboRepository.findAll().size();
        // set the field null
        combo.setPoint(null);

        // Create the Combo, which fails.

        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isBadRequest());

        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLikedIsRequired() throws Exception {
        int databaseSizeBeforeTest = comboRepository.findAll().size();
        // set the field null
        combo.setLiked(null);

        // Create the Combo, which fails.

        restComboMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isBadRequest());

        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCombos() throws Exception {
        // Initialize the database
        comboRepository.saveAndFlush(combo);

        // Get all the comboList
        restComboMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(combo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED)));
    }

    @Test
    @Transactional
    void getCombo() throws Exception {
        // Initialize the database
        comboRepository.saveAndFlush(combo);

        // Get the combo
        restComboMockMvc
            .perform(get(ENTITY_API_URL_ID, combo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(combo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED));
    }

    @Test
    @Transactional
    void getNonExistingCombo() throws Exception {
        // Get the combo
        restComboMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCombo() throws Exception {
        // Initialize the database
        comboRepository.saveAndFlush(combo);

        int databaseSizeBeforeUpdate = comboRepository.findAll().size();

        // Update the combo
        Combo updatedCombo = comboRepository.findById(combo.getId()).get();
        // Disconnect from session so that the updates on updatedCombo are not directly saved in db
        em.detach(updatedCombo);
        updatedCombo
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .weight(UPDATED_WEIGHT)
            .point(UPDATED_POINT)
            .description(UPDATED_DESCRIPTION)
            .liked(UPDATED_LIKED);

        restComboMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCombo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCombo))
            )
            .andExpect(status().isOk());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
        Combo testCombo = comboList.get(comboList.size() - 1);
        assertThat(testCombo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCombo.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testCombo.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCombo.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testCombo.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testCombo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCombo.getLiked()).isEqualTo(UPDATED_LIKED);
    }

    @Test
    @Transactional
    void putNonExistingCombo() throws Exception {
        int databaseSizeBeforeUpdate = comboRepository.findAll().size();
        combo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComboMockMvc
            .perform(
                put(ENTITY_API_URL_ID, combo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(combo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCombo() throws Exception {
        int databaseSizeBeforeUpdate = comboRepository.findAll().size();
        combo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComboMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(combo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCombo() throws Exception {
        int databaseSizeBeforeUpdate = comboRepository.findAll().size();
        combo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComboMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateComboWithPatch() throws Exception {
        // Initialize the database
        comboRepository.saveAndFlush(combo);

        int databaseSizeBeforeUpdate = comboRepository.findAll().size();

        // Update the combo using partial update
        Combo partialUpdatedCombo = new Combo();
        partialUpdatedCombo.setId(combo.getId());

        partialUpdatedCombo
            .discount(UPDATED_DISCOUNT)
            .weight(UPDATED_WEIGHT)
            .point(UPDATED_POINT)
            .description(UPDATED_DESCRIPTION)
            .liked(UPDATED_LIKED);

        restComboMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCombo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCombo))
            )
            .andExpect(status().isOk());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
        Combo testCombo = comboList.get(comboList.size() - 1);
        assertThat(testCombo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCombo.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testCombo.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCombo.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testCombo.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testCombo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCombo.getLiked()).isEqualTo(UPDATED_LIKED);
    }

    @Test
    @Transactional
    void fullUpdateComboWithPatch() throws Exception {
        // Initialize the database
        comboRepository.saveAndFlush(combo);

        int databaseSizeBeforeUpdate = comboRepository.findAll().size();

        // Update the combo using partial update
        Combo partialUpdatedCombo = new Combo();
        partialUpdatedCombo.setId(combo.getId());

        partialUpdatedCombo
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .weight(UPDATED_WEIGHT)
            .point(UPDATED_POINT)
            .description(UPDATED_DESCRIPTION)
            .liked(UPDATED_LIKED);

        restComboMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCombo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCombo))
            )
            .andExpect(status().isOk());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
        Combo testCombo = comboList.get(comboList.size() - 1);
        assertThat(testCombo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCombo.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testCombo.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCombo.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testCombo.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testCombo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCombo.getLiked()).isEqualTo(UPDATED_LIKED);
    }

    @Test
    @Transactional
    void patchNonExistingCombo() throws Exception {
        int databaseSizeBeforeUpdate = comboRepository.findAll().size();
        combo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComboMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, combo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(combo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCombo() throws Exception {
        int databaseSizeBeforeUpdate = comboRepository.findAll().size();
        combo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComboMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(combo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCombo() throws Exception {
        int databaseSizeBeforeUpdate = comboRepository.findAll().size();
        combo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComboMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(combo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Combo in the database
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCombo() throws Exception {
        // Initialize the database
        comboRepository.saveAndFlush(combo);

        int databaseSizeBeforeDelete = comboRepository.findAll().size();

        // Delete the combo
        restComboMockMvc
            .perform(delete(ENTITY_API_URL_ID, combo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Combo> comboList = comboRepository.findAll();
        assertThat(comboList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
