package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.OrderDetails;
import com.dental.repository.OrderDetailsRepository;
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
 * Integration tests for the {@link OrderDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderDetailsResourceIT {

    private static final Integer DEFAULT_ORDER_ID = 1;
    private static final Integer UPDATED_ORDER_ID = 2;

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final Integer DEFAULT_PRODUCT_TYPE = 1;
    private static final Integer UPDATED_PRODUCT_TYPE = 2;

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final Double DEFAULT_SUBTOTAL_AMOUNT = 1D;
    private static final Double UPDATED_SUBTOTAL_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_AMOUNT = 2D;

    private static final Double DEFAULT_VARIANT_ID = 1D;
    private static final Double UPDATED_VARIANT_ID = 2D;

    private static final Long DEFAULT_SHOPIFY_VARIANT_ID = 1L;
    private static final Long UPDATED_SHOPIFY_VARIANT_ID = 2L;

    private static final Long DEFAULT_SHOPIFY_PRODUCT_ID = 1L;
    private static final Long UPDATED_SHOPIFY_PRODUCT_ID = 2L;

    private static final Long DEFAULT_SHOPIFY_ORDER_ID = 1L;
    private static final Long UPDATED_SHOPIFY_ORDER_ID = 2L;

    private static final Long DEFAULT_SHOPIFY_ID = 1L;
    private static final Long UPDATED_SHOPIFY_ID = 2L;

    private static final String ENTITY_API_URL = "/api/order-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderDetailsMockMvc;

    private OrderDetails orderDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderDetails createEntity(EntityManager em) {
        OrderDetails orderDetails = new OrderDetails()
            .orderId(DEFAULT_ORDER_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .productType(DEFAULT_PRODUCT_TYPE)
            .avatar(DEFAULT_AVATAR)
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE)
            .point(DEFAULT_POINT)
            .qty(DEFAULT_QTY)
            .discount(DEFAULT_DISCOUNT)
            .subtotalAmount(DEFAULT_SUBTOTAL_AMOUNT)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .variantId(DEFAULT_VARIANT_ID)
            .shopifyVariantId(DEFAULT_SHOPIFY_VARIANT_ID)
            .shopifyProductId(DEFAULT_SHOPIFY_PRODUCT_ID)
            .shopifyOrderId(DEFAULT_SHOPIFY_ORDER_ID)
            .shopifyId(DEFAULT_SHOPIFY_ID);
        return orderDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderDetails createUpdatedEntity(EntityManager em) {
        OrderDetails orderDetails = new OrderDetails()
            .orderId(UPDATED_ORDER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productType(UPDATED_PRODUCT_TYPE)
            .avatar(UPDATED_AVATAR)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .point(UPDATED_POINT)
            .qty(UPDATED_QTY)
            .discount(UPDATED_DISCOUNT)
            .subtotalAmount(UPDATED_SUBTOTAL_AMOUNT)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .variantId(UPDATED_VARIANT_ID)
            .shopifyVariantId(UPDATED_SHOPIFY_VARIANT_ID)
            .shopifyProductId(UPDATED_SHOPIFY_PRODUCT_ID)
            .shopifyOrderId(UPDATED_SHOPIFY_ORDER_ID)
            .shopifyId(UPDATED_SHOPIFY_ID);
        return orderDetails;
    }

    @BeforeEach
    public void initTest() {
        orderDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createOrderDetails() throws Exception {
        int databaseSizeBeforeCreate = orderDetailsRepository.findAll().size();
        // Create the OrderDetails
        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isCreated());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        OrderDetails testOrderDetails = orderDetailsList.get(orderDetailsList.size() - 1);
        assertThat(testOrderDetails.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOrderDetails.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testOrderDetails.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testOrderDetails.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testOrderDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrderDetails.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testOrderDetails.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testOrderDetails.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testOrderDetails.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testOrderDetails.getSubtotalAmount()).isEqualTo(DEFAULT_SUBTOTAL_AMOUNT);
        assertThat(testOrderDetails.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testOrderDetails.getVariantId()).isEqualTo(DEFAULT_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyVariantId()).isEqualTo(DEFAULT_SHOPIFY_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyProductId()).isEqualTo(DEFAULT_SHOPIFY_PRODUCT_ID);
        assertThat(testOrderDetails.getShopifyOrderId()).isEqualTo(DEFAULT_SHOPIFY_ORDER_ID);
        assertThat(testOrderDetails.getShopifyId()).isEqualTo(DEFAULT_SHOPIFY_ID);
    }

    @Test
    @Transactional
    void createOrderDetailsWithExistingId() throws Exception {
        // Create the OrderDetails with an existing ID
        orderDetails.setId(1L);

        int databaseSizeBeforeCreate = orderDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setOrderId(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setProductId(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProductTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setProductType(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setPrice(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setPoint(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setQty(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setDiscount(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubtotalAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setSubtotalAmount(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDetailsRepository.findAll().size();
        // set the field null
        orderDetails.setTotalAmount(null);

        // Create the OrderDetails, which fails.

        restOrderDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isBadRequest());

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllOrderDetails() throws Exception {
        // Initialize the database
        orderDetailsRepository.saveAndFlush(orderDetails);

        // Get all the orderDetailsList
        restOrderDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE)))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].subtotalAmount").value(hasItem(DEFAULT_SUBTOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].variantId").value(hasItem(DEFAULT_VARIANT_ID.doubleValue())))
            .andExpect(jsonPath("$.[*].shopifyVariantId").value(hasItem(DEFAULT_SHOPIFY_VARIANT_ID.intValue())))
            .andExpect(jsonPath("$.[*].shopifyProductId").value(hasItem(DEFAULT_SHOPIFY_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].shopifyOrderId").value(hasItem(DEFAULT_SHOPIFY_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].shopifyId").value(hasItem(DEFAULT_SHOPIFY_ID.intValue())));
    }

    @Test
    @Transactional
    void getOrderDetails() throws Exception {
        // Initialize the database
        orderDetailsRepository.saveAndFlush(orderDetails);

        // Get the orderDetails
        restOrderDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, orderDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderDetails.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.subtotalAmount").value(DEFAULT_SUBTOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.variantId").value(DEFAULT_VARIANT_ID.doubleValue()))
            .andExpect(jsonPath("$.shopifyVariantId").value(DEFAULT_SHOPIFY_VARIANT_ID.intValue()))
            .andExpect(jsonPath("$.shopifyProductId").value(DEFAULT_SHOPIFY_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.shopifyOrderId").value(DEFAULT_SHOPIFY_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.shopifyId").value(DEFAULT_SHOPIFY_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingOrderDetails() throws Exception {
        // Get the orderDetails
        restOrderDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOrderDetails() throws Exception {
        // Initialize the database
        orderDetailsRepository.saveAndFlush(orderDetails);

        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();

        // Update the orderDetails
        OrderDetails updatedOrderDetails = orderDetailsRepository.findById(orderDetails.getId()).get();
        // Disconnect from session so that the updates on updatedOrderDetails are not directly saved in db
        em.detach(updatedOrderDetails);
        updatedOrderDetails
            .orderId(UPDATED_ORDER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productType(UPDATED_PRODUCT_TYPE)
            .avatar(UPDATED_AVATAR)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .point(UPDATED_POINT)
            .qty(UPDATED_QTY)
            .discount(UPDATED_DISCOUNT)
            .subtotalAmount(UPDATED_SUBTOTAL_AMOUNT)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .variantId(UPDATED_VARIANT_ID)
            .shopifyVariantId(UPDATED_SHOPIFY_VARIANT_ID)
            .shopifyProductId(UPDATED_SHOPIFY_PRODUCT_ID)
            .shopifyOrderId(UPDATED_SHOPIFY_ORDER_ID)
            .shopifyId(UPDATED_SHOPIFY_ID);

        restOrderDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOrderDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOrderDetails))
            )
            .andExpect(status().isOk());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
        OrderDetails testOrderDetails = orderDetailsList.get(orderDetailsList.size() - 1);
        assertThat(testOrderDetails.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderDetails.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testOrderDetails.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testOrderDetails.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testOrderDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrderDetails.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testOrderDetails.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testOrderDetails.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testOrderDetails.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testOrderDetails.getSubtotalAmount()).isEqualTo(UPDATED_SUBTOTAL_AMOUNT);
        assertThat(testOrderDetails.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrderDetails.getVariantId()).isEqualTo(UPDATED_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyVariantId()).isEqualTo(UPDATED_SHOPIFY_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyProductId()).isEqualTo(UPDATED_SHOPIFY_PRODUCT_ID);
        assertThat(testOrderDetails.getShopifyOrderId()).isEqualTo(UPDATED_SHOPIFY_ORDER_ID);
        assertThat(testOrderDetails.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
    }

    @Test
    @Transactional
    void putNonExistingOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();
        orderDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();
        orderDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();
        orderDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderDetailsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderDetails)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderDetailsWithPatch() throws Exception {
        // Initialize the database
        orderDetailsRepository.saveAndFlush(orderDetails);

        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();

        // Update the orderDetails using partial update
        OrderDetails partialUpdatedOrderDetails = new OrderDetails();
        partialUpdatedOrderDetails.setId(orderDetails.getId());

        partialUpdatedOrderDetails
            .orderId(UPDATED_ORDER_ID)
            .productType(UPDATED_PRODUCT_TYPE)
            .avatar(UPDATED_AVATAR)
            .price(UPDATED_PRICE)
            .qty(UPDATED_QTY)
            .subtotalAmount(UPDATED_SUBTOTAL_AMOUNT)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .shopifyVariantId(UPDATED_SHOPIFY_VARIANT_ID)
            .shopifyProductId(UPDATED_SHOPIFY_PRODUCT_ID)
            .shopifyOrderId(UPDATED_SHOPIFY_ORDER_ID)
            .shopifyId(UPDATED_SHOPIFY_ID);

        restOrderDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderDetails))
            )
            .andExpect(status().isOk());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
        OrderDetails testOrderDetails = orderDetailsList.get(orderDetailsList.size() - 1);
        assertThat(testOrderDetails.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderDetails.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testOrderDetails.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testOrderDetails.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testOrderDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrderDetails.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testOrderDetails.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testOrderDetails.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testOrderDetails.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testOrderDetails.getSubtotalAmount()).isEqualTo(UPDATED_SUBTOTAL_AMOUNT);
        assertThat(testOrderDetails.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrderDetails.getVariantId()).isEqualTo(DEFAULT_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyVariantId()).isEqualTo(UPDATED_SHOPIFY_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyProductId()).isEqualTo(UPDATED_SHOPIFY_PRODUCT_ID);
        assertThat(testOrderDetails.getShopifyOrderId()).isEqualTo(UPDATED_SHOPIFY_ORDER_ID);
        assertThat(testOrderDetails.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
    }

    @Test
    @Transactional
    void fullUpdateOrderDetailsWithPatch() throws Exception {
        // Initialize the database
        orderDetailsRepository.saveAndFlush(orderDetails);

        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();

        // Update the orderDetails using partial update
        OrderDetails partialUpdatedOrderDetails = new OrderDetails();
        partialUpdatedOrderDetails.setId(orderDetails.getId());

        partialUpdatedOrderDetails
            .orderId(UPDATED_ORDER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productType(UPDATED_PRODUCT_TYPE)
            .avatar(UPDATED_AVATAR)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .point(UPDATED_POINT)
            .qty(UPDATED_QTY)
            .discount(UPDATED_DISCOUNT)
            .subtotalAmount(UPDATED_SUBTOTAL_AMOUNT)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .variantId(UPDATED_VARIANT_ID)
            .shopifyVariantId(UPDATED_SHOPIFY_VARIANT_ID)
            .shopifyProductId(UPDATED_SHOPIFY_PRODUCT_ID)
            .shopifyOrderId(UPDATED_SHOPIFY_ORDER_ID)
            .shopifyId(UPDATED_SHOPIFY_ID);

        restOrderDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderDetails))
            )
            .andExpect(status().isOk());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
        OrderDetails testOrderDetails = orderDetailsList.get(orderDetailsList.size() - 1);
        assertThat(testOrderDetails.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderDetails.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testOrderDetails.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testOrderDetails.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testOrderDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrderDetails.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testOrderDetails.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testOrderDetails.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testOrderDetails.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testOrderDetails.getSubtotalAmount()).isEqualTo(UPDATED_SUBTOTAL_AMOUNT);
        assertThat(testOrderDetails.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrderDetails.getVariantId()).isEqualTo(UPDATED_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyVariantId()).isEqualTo(UPDATED_SHOPIFY_VARIANT_ID);
        assertThat(testOrderDetails.getShopifyProductId()).isEqualTo(UPDATED_SHOPIFY_PRODUCT_ID);
        assertThat(testOrderDetails.getShopifyOrderId()).isEqualTo(UPDATED_SHOPIFY_ORDER_ID);
        assertThat(testOrderDetails.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
    }

    @Test
    @Transactional
    void patchNonExistingOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();
        orderDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();
        orderDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = orderDetailsRepository.findAll().size();
        orderDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(orderDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderDetails in the database
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderDetails() throws Exception {
        // Initialize the database
        orderDetailsRepository.saveAndFlush(orderDetails);

        int databaseSizeBeforeDelete = orderDetailsRepository.findAll().size();

        // Delete the orderDetails
        restOrderDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        assertThat(orderDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
