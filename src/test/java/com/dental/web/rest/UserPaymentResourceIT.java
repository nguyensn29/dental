package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.UserPayment;
import com.dental.repository.UserPaymentRepository;
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
 * Integration tests for the {@link UserPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserPaymentResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIAL = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_WARD = "AAAAAAAAAA";
    private static final String UPDATED_WARD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserPaymentMockMvc;

    private UserPayment userPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPayment createEntity(EntityManager em) {
        UserPayment userPayment = new UserPayment()
            .userId(DEFAULT_USER_ID)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .adress(DEFAULT_ADRESS)
            .provincial(DEFAULT_PROVINCIAL)
            .district(DEFAULT_DISTRICT)
            .ward(DEFAULT_WARD)
            .email(DEFAULT_EMAIL)
            .street(DEFAULT_STREET);
        return userPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPayment createUpdatedEntity(EntityManager em) {
        UserPayment userPayment = new UserPayment()
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .adress(UPDATED_ADRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .ward(UPDATED_WARD)
            .email(UPDATED_EMAIL)
            .street(UPDATED_STREET);
        return userPayment;
    }

    @BeforeEach
    public void initTest() {
        userPayment = createEntity(em);
    }

    @Test
    @Transactional
    void createUserPayment() throws Exception {
        int databaseSizeBeforeCreate = userPaymentRepository.findAll().size();
        // Create the UserPayment
        restUserPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPayment)))
            .andExpect(status().isCreated());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        UserPayment testUserPayment = userPaymentList.get(userPaymentList.size() - 1);
        assertThat(testUserPayment.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserPayment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserPayment.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserPayment.getAdress()).isEqualTo(DEFAULT_ADRESS);
        assertThat(testUserPayment.getProvincial()).isEqualTo(DEFAULT_PROVINCIAL);
        assertThat(testUserPayment.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testUserPayment.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testUserPayment.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserPayment.getStreet()).isEqualTo(DEFAULT_STREET);
    }

    @Test
    @Transactional
    void createUserPaymentWithExistingId() throws Exception {
        // Create the UserPayment with an existing ID
        userPayment.setId(1L);

        int databaseSizeBeforeCreate = userPaymentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPayment)))
            .andExpect(status().isBadRequest());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPaymentRepository.findAll().size();
        // set the field null
        userPayment.setUserId(null);

        // Create the UserPayment, which fails.

        restUserPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPayment)))
            .andExpect(status().isBadRequest());

        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUserPayments() throws Exception {
        // Initialize the database
        userPaymentRepository.saveAndFlush(userPayment);

        // Get all the userPaymentList
        restUserPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].provincial").value(hasItem(DEFAULT_PROVINCIAL)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].ward").value(hasItem(DEFAULT_WARD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)));
    }

    @Test
    @Transactional
    void getUserPayment() throws Exception {
        // Initialize the database
        userPaymentRepository.saveAndFlush(userPayment);

        // Get the userPayment
        restUserPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, userPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userPayment.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.adress").value(DEFAULT_ADRESS))
            .andExpect(jsonPath("$.provincial").value(DEFAULT_PROVINCIAL))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.ward").value(DEFAULT_WARD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET));
    }

    @Test
    @Transactional
    void getNonExistingUserPayment() throws Exception {
        // Get the userPayment
        restUserPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUserPayment() throws Exception {
        // Initialize the database
        userPaymentRepository.saveAndFlush(userPayment);

        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();

        // Update the userPayment
        UserPayment updatedUserPayment = userPaymentRepository.findById(userPayment.getId()).get();
        // Disconnect from session so that the updates on updatedUserPayment are not directly saved in db
        em.detach(updatedUserPayment);
        updatedUserPayment
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .adress(UPDATED_ADRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .ward(UPDATED_WARD)
            .email(UPDATED_EMAIL)
            .street(UPDATED_STREET);

        restUserPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUserPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserPayment))
            )
            .andExpect(status().isOk());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
        UserPayment testUserPayment = userPaymentList.get(userPaymentList.size() - 1);
        assertThat(testUserPayment.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserPayment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserPayment.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserPayment.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testUserPayment.getProvincial()).isEqualTo(UPDATED_PROVINCIAL);
        assertThat(testUserPayment.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testUserPayment.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testUserPayment.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserPayment.getStreet()).isEqualTo(UPDATED_STREET);
    }

    @Test
    @Transactional
    void putNonExistingUserPayment() throws Exception {
        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();
        userPayment.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserPayment() throws Exception {
        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();
        userPayment.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserPayment() throws Exception {
        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();
        userPayment.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserPaymentWithPatch() throws Exception {
        // Initialize the database
        userPaymentRepository.saveAndFlush(userPayment);

        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();

        // Update the userPayment using partial update
        UserPayment partialUpdatedUserPayment = new UserPayment();
        partialUpdatedUserPayment.setId(userPayment.getId());

        partialUpdatedUserPayment
            .name(UPDATED_NAME)
            .adress(UPDATED_ADRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .ward(UPDATED_WARD)
            .street(UPDATED_STREET);

        restUserPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserPayment))
            )
            .andExpect(status().isOk());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
        UserPayment testUserPayment = userPaymentList.get(userPaymentList.size() - 1);
        assertThat(testUserPayment.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserPayment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserPayment.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserPayment.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testUserPayment.getProvincial()).isEqualTo(UPDATED_PROVINCIAL);
        assertThat(testUserPayment.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testUserPayment.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testUserPayment.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserPayment.getStreet()).isEqualTo(UPDATED_STREET);
    }

    @Test
    @Transactional
    void fullUpdateUserPaymentWithPatch() throws Exception {
        // Initialize the database
        userPaymentRepository.saveAndFlush(userPayment);

        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();

        // Update the userPayment using partial update
        UserPayment partialUpdatedUserPayment = new UserPayment();
        partialUpdatedUserPayment.setId(userPayment.getId());

        partialUpdatedUserPayment
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .adress(UPDATED_ADRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .ward(UPDATED_WARD)
            .email(UPDATED_EMAIL)
            .street(UPDATED_STREET);

        restUserPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserPayment))
            )
            .andExpect(status().isOk());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
        UserPayment testUserPayment = userPaymentList.get(userPaymentList.size() - 1);
        assertThat(testUserPayment.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserPayment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserPayment.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserPayment.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testUserPayment.getProvincial()).isEqualTo(UPDATED_PROVINCIAL);
        assertThat(testUserPayment.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testUserPayment.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testUserPayment.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserPayment.getStreet()).isEqualTo(UPDATED_STREET);
    }

    @Test
    @Transactional
    void patchNonExistingUserPayment() throws Exception {
        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();
        userPayment.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserPayment() throws Exception {
        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();
        userPayment.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserPayment() throws Exception {
        int databaseSizeBeforeUpdate = userPaymentRepository.findAll().size();
        userPayment.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userPayment))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserPayment in the database
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserPayment() throws Exception {
        // Initialize the database
        userPaymentRepository.saveAndFlush(userPayment);

        int databaseSizeBeforeDelete = userPaymentRepository.findAll().size();

        // Delete the userPayment
        restUserPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, userPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();
        assertThat(userPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
