package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.Rank;
import com.dental.repository.RankRepository;
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
 * Integration tests for the {@link RankResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RankResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TURNOVER_CONDITION = 1D;
    private static final Double UPDATED_TURNOVER_CONDITION = 2D;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final String ENTITY_API_URL = "/api/ranks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRankMockMvc;

    private Rank rank;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rank createEntity(EntityManager em) {
        Rank rank = new Rank().name(DEFAULT_NAME).turnoverCondition(DEFAULT_TURNOVER_CONDITION).discount(DEFAULT_DISCOUNT);
        return rank;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rank createUpdatedEntity(EntityManager em) {
        Rank rank = new Rank().name(UPDATED_NAME).turnoverCondition(UPDATED_TURNOVER_CONDITION).discount(UPDATED_DISCOUNT);
        return rank;
    }

    @BeforeEach
    public void initTest() {
        rank = createEntity(em);
    }

    @Test
    @Transactional
    void createRank() throws Exception {
        int databaseSizeBeforeCreate = rankRepository.findAll().size();
        // Create the Rank
        restRankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isCreated());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeCreate + 1);
        Rank testRank = rankList.get(rankList.size() - 1);
        assertThat(testRank.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRank.getTurnoverCondition()).isEqualTo(DEFAULT_TURNOVER_CONDITION);
        assertThat(testRank.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
    }

    @Test
    @Transactional
    void createRankWithExistingId() throws Exception {
        // Create the Rank with an existing ID
        rank.setId(1L);

        int databaseSizeBeforeCreate = rankRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isBadRequest());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankRepository.findAll().size();
        // set the field null
        rank.setName(null);

        // Create the Rank, which fails.

        restRankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isBadRequest());

        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTurnoverConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankRepository.findAll().size();
        // set the field null
        rank.setTurnoverCondition(null);

        // Create the Rank, which fails.

        restRankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isBadRequest());

        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankRepository.findAll().size();
        // set the field null
        rank.setDiscount(null);

        // Create the Rank, which fails.

        restRankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isBadRequest());

        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRanks() throws Exception {
        // Initialize the database
        rankRepository.saveAndFlush(rank);

        // Get all the rankList
        restRankMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rank.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].turnoverCondition").value(hasItem(DEFAULT_TURNOVER_CONDITION.doubleValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())));
    }

    @Test
    @Transactional
    void getRank() throws Exception {
        // Initialize the database
        rankRepository.saveAndFlush(rank);

        // Get the rank
        restRankMockMvc
            .perform(get(ENTITY_API_URL_ID, rank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rank.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.turnoverCondition").value(DEFAULT_TURNOVER_CONDITION.doubleValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingRank() throws Exception {
        // Get the rank
        restRankMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRank() throws Exception {
        // Initialize the database
        rankRepository.saveAndFlush(rank);

        int databaseSizeBeforeUpdate = rankRepository.findAll().size();

        // Update the rank
        Rank updatedRank = rankRepository.findById(rank.getId()).get();
        // Disconnect from session so that the updates on updatedRank are not directly saved in db
        em.detach(updatedRank);
        updatedRank.name(UPDATED_NAME).turnoverCondition(UPDATED_TURNOVER_CONDITION).discount(UPDATED_DISCOUNT);

        restRankMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRank.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRank))
            )
            .andExpect(status().isOk());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
        Rank testRank = rankList.get(rankList.size() - 1);
        assertThat(testRank.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRank.getTurnoverCondition()).isEqualTo(UPDATED_TURNOVER_CONDITION);
        assertThat(testRank.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    void putNonExistingRank() throws Exception {
        int databaseSizeBeforeUpdate = rankRepository.findAll().size();
        rank.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rank.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rank))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRank() throws Exception {
        int databaseSizeBeforeUpdate = rankRepository.findAll().size();
        rank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rank))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRank() throws Exception {
        int databaseSizeBeforeUpdate = rankRepository.findAll().size();
        rank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRankWithPatch() throws Exception {
        // Initialize the database
        rankRepository.saveAndFlush(rank);

        int databaseSizeBeforeUpdate = rankRepository.findAll().size();

        // Update the rank using partial update
        Rank partialUpdatedRank = new Rank();
        partialUpdatedRank.setId(rank.getId());

        partialUpdatedRank.turnoverCondition(UPDATED_TURNOVER_CONDITION).discount(UPDATED_DISCOUNT);

        restRankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRank.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRank))
            )
            .andExpect(status().isOk());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
        Rank testRank = rankList.get(rankList.size() - 1);
        assertThat(testRank.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRank.getTurnoverCondition()).isEqualTo(UPDATED_TURNOVER_CONDITION);
        assertThat(testRank.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    void fullUpdateRankWithPatch() throws Exception {
        // Initialize the database
        rankRepository.saveAndFlush(rank);

        int databaseSizeBeforeUpdate = rankRepository.findAll().size();

        // Update the rank using partial update
        Rank partialUpdatedRank = new Rank();
        partialUpdatedRank.setId(rank.getId());

        partialUpdatedRank.name(UPDATED_NAME).turnoverCondition(UPDATED_TURNOVER_CONDITION).discount(UPDATED_DISCOUNT);

        restRankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRank.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRank))
            )
            .andExpect(status().isOk());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
        Rank testRank = rankList.get(rankList.size() - 1);
        assertThat(testRank.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRank.getTurnoverCondition()).isEqualTo(UPDATED_TURNOVER_CONDITION);
        assertThat(testRank.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    void patchNonExistingRank() throws Exception {
        int databaseSizeBeforeUpdate = rankRepository.findAll().size();
        rank.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rank.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rank))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRank() throws Exception {
        int databaseSizeBeforeUpdate = rankRepository.findAll().size();
        rank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rank))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRank() throws Exception {
        int databaseSizeBeforeUpdate = rankRepository.findAll().size();
        rank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rank)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rank in the database
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRank() throws Exception {
        // Initialize the database
        rankRepository.saveAndFlush(rank);

        int databaseSizeBeforeDelete = rankRepository.findAll().size();

        // Delete the rank
        restRankMockMvc
            .perform(delete(ENTITY_API_URL_ID, rank.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rank> rankList = rankRepository.findAll();
        assertThat(rankList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
