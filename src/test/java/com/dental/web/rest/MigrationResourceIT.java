package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.Migration;
import com.dental.repository.MigrationRepository;
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
 * Integration tests for the {@link MigrationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MigrationResourceIT {

    private static final String DEFAULT_MIGRATION = "AAAAAAAAAA";
    private static final String UPDATED_MIGRATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_BATCH = 1;
    private static final Integer UPDATED_BATCH = 2;

    private static final String ENTITY_API_URL = "/api/migrations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MigrationRepository migrationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMigrationMockMvc;

    private Migration migration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Migration createEntity(EntityManager em) {
        Migration migration = new Migration().migration(DEFAULT_MIGRATION).batch(DEFAULT_BATCH);
        return migration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Migration createUpdatedEntity(EntityManager em) {
        Migration migration = new Migration().migration(UPDATED_MIGRATION).batch(UPDATED_BATCH);
        return migration;
    }

    @BeforeEach
    public void initTest() {
        migration = createEntity(em);
    }

    @Test
    @Transactional
    void createMigration() throws Exception {
        int databaseSizeBeforeCreate = migrationRepository.findAll().size();
        // Create the Migration
        restMigrationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(migration)))
            .andExpect(status().isCreated());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeCreate + 1);
        Migration testMigration = migrationList.get(migrationList.size() - 1);
        assertThat(testMigration.getMigration()).isEqualTo(DEFAULT_MIGRATION);
        assertThat(testMigration.getBatch()).isEqualTo(DEFAULT_BATCH);
    }

    @Test
    @Transactional
    void createMigrationWithExistingId() throws Exception {
        // Create the Migration with an existing ID
        migration.setId(1L);

        int databaseSizeBeforeCreate = migrationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMigrationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(migration)))
            .andExpect(status().isBadRequest());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMigrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = migrationRepository.findAll().size();
        // set the field null
        migration.setMigration(null);

        // Create the Migration, which fails.

        restMigrationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(migration)))
            .andExpect(status().isBadRequest());

        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchIsRequired() throws Exception {
        int databaseSizeBeforeTest = migrationRepository.findAll().size();
        // set the field null
        migration.setBatch(null);

        // Create the Migration, which fails.

        restMigrationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(migration)))
            .andExpect(status().isBadRequest());

        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMigrations() throws Exception {
        // Initialize the database
        migrationRepository.saveAndFlush(migration);

        // Get all the migrationList
        restMigrationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(migration.getId().intValue())))
            .andExpect(jsonPath("$.[*].migration").value(hasItem(DEFAULT_MIGRATION)))
            .andExpect(jsonPath("$.[*].batch").value(hasItem(DEFAULT_BATCH)));
    }

    @Test
    @Transactional
    void getMigration() throws Exception {
        // Initialize the database
        migrationRepository.saveAndFlush(migration);

        // Get the migration
        restMigrationMockMvc
            .perform(get(ENTITY_API_URL_ID, migration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(migration.getId().intValue()))
            .andExpect(jsonPath("$.migration").value(DEFAULT_MIGRATION))
            .andExpect(jsonPath("$.batch").value(DEFAULT_BATCH));
    }

    @Test
    @Transactional
    void getNonExistingMigration() throws Exception {
        // Get the migration
        restMigrationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMigration() throws Exception {
        // Initialize the database
        migrationRepository.saveAndFlush(migration);

        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();

        // Update the migration
        Migration updatedMigration = migrationRepository.findById(migration.getId()).get();
        // Disconnect from session so that the updates on updatedMigration are not directly saved in db
        em.detach(updatedMigration);
        updatedMigration.migration(UPDATED_MIGRATION).batch(UPDATED_BATCH);

        restMigrationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMigration.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMigration))
            )
            .andExpect(status().isOk());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
        Migration testMigration = migrationList.get(migrationList.size() - 1);
        assertThat(testMigration.getMigration()).isEqualTo(UPDATED_MIGRATION);
        assertThat(testMigration.getBatch()).isEqualTo(UPDATED_BATCH);
    }

    @Test
    @Transactional
    void putNonExistingMigration() throws Exception {
        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();
        migration.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMigrationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, migration.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(migration))
            )
            .andExpect(status().isBadRequest());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMigration() throws Exception {
        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();
        migration.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMigrationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(migration))
            )
            .andExpect(status().isBadRequest());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMigration() throws Exception {
        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();
        migration.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMigrationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(migration)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMigrationWithPatch() throws Exception {
        // Initialize the database
        migrationRepository.saveAndFlush(migration);

        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();

        // Update the migration using partial update
        Migration partialUpdatedMigration = new Migration();
        partialUpdatedMigration.setId(migration.getId());

        partialUpdatedMigration.migration(UPDATED_MIGRATION).batch(UPDATED_BATCH);

        restMigrationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMigration.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMigration))
            )
            .andExpect(status().isOk());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
        Migration testMigration = migrationList.get(migrationList.size() - 1);
        assertThat(testMigration.getMigration()).isEqualTo(UPDATED_MIGRATION);
        assertThat(testMigration.getBatch()).isEqualTo(UPDATED_BATCH);
    }

    @Test
    @Transactional
    void fullUpdateMigrationWithPatch() throws Exception {
        // Initialize the database
        migrationRepository.saveAndFlush(migration);

        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();

        // Update the migration using partial update
        Migration partialUpdatedMigration = new Migration();
        partialUpdatedMigration.setId(migration.getId());

        partialUpdatedMigration.migration(UPDATED_MIGRATION).batch(UPDATED_BATCH);

        restMigrationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMigration.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMigration))
            )
            .andExpect(status().isOk());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
        Migration testMigration = migrationList.get(migrationList.size() - 1);
        assertThat(testMigration.getMigration()).isEqualTo(UPDATED_MIGRATION);
        assertThat(testMigration.getBatch()).isEqualTo(UPDATED_BATCH);
    }

    @Test
    @Transactional
    void patchNonExistingMigration() throws Exception {
        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();
        migration.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMigrationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, migration.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(migration))
            )
            .andExpect(status().isBadRequest());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMigration() throws Exception {
        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();
        migration.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMigrationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(migration))
            )
            .andExpect(status().isBadRequest());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMigration() throws Exception {
        int databaseSizeBeforeUpdate = migrationRepository.findAll().size();
        migration.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMigrationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(migration))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Migration in the database
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMigration() throws Exception {
        // Initialize the database
        migrationRepository.saveAndFlush(migration);

        int databaseSizeBeforeDelete = migrationRepository.findAll().size();

        // Delete the migration
        restMigrationMockMvc
            .perform(delete(ENTITY_API_URL_ID, migration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Migration> migrationList = migrationRepository.findAll();
        assertThat(migrationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
