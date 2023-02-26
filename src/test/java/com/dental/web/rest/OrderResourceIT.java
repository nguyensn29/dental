package com.dental.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dental.IntegrationTest;
import com.dental.domain.Order;
import com.dental.repository.OrderRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link OrderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderResourceIT {

    private static final String DEFAULT_ORDER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANS_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANS_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYMENT_STATUS = 1;
    private static final Integer UPDATED_PAYMENT_STATUS = 2;

    private static final Double DEFAULT_TOTAL_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_DISCOUNT = 1D;
    private static final Double UPDATED_TOTAL_DISCOUNT = 2D;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Integer DEFAULT_PAY_METHOD = 1;
    private static final Integer UPDATED_PAY_METHOD = 2;

    private static final Instant DEFAULT_SHIPPING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SHIPPING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SHIPPING_STATUS = 1;
    private static final Integer UPDATED_SHIPPING_STATUS = 2;

    private static final String DEFAULT_DELIVERY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIAL = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final Long DEFAULT_SHOPIFY_ID = 1L;
    private static final Long UPDATED_SHOPIFY_ID = 2L;

    private static final Long DEFAULT_SHOPIFY_USER_ID = 1L;
    private static final Long UPDATED_SHOPIFY_USER_ID = 2L;

    private static final Integer DEFAULT_TOTAL_WEIGHT = 1;
    private static final Integer UPDATED_TOTAL_WEIGHT = 2;

    private static final String DEFAULT_FULFILLMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FULFILLMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_GATEWAY = "AAAAAAAAAA";
    private static final String UPDATED_GATEWAY = "BBBBBBBBBB";

    private static final String DEFAULT_WARD = "AAAAAAAAAA";
    private static final String UPDATED_WARD = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final Instant DEFAULT_FULFILLMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FULFILLMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CANCELLED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CANCELLED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CANCEL_REASON = "AAAAAAAAAA";
    private static final String UPDATED_CANCEL_REASON = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/orders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderMockMvc;

    private Order order;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createEntity(EntityManager em) {
        Order order = new Order()
            .orderCode(DEFAULT_ORDER_CODE)
            .transId(DEFAULT_TRANS_ID)
            .paymentStatus(DEFAULT_PAYMENT_STATUS)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .totalDiscount(DEFAULT_TOTAL_DISCOUNT)
            .point(DEFAULT_POINT)
            .userId(DEFAULT_USER_ID)
            .payMethod(DEFAULT_PAY_METHOD)
            .shippingDate(DEFAULT_SHIPPING_DATE)
            .shippingStatus(DEFAULT_SHIPPING_STATUS)
            .deliveryCode(DEFAULT_DELIVERY_CODE)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS)
            .provincial(DEFAULT_PROVINCIAL)
            .district(DEFAULT_DISTRICT)
            .shopifyId(DEFAULT_SHOPIFY_ID)
            .shopifyUserId(DEFAULT_SHOPIFY_USER_ID)
            .totalWeight(DEFAULT_TOTAL_WEIGHT)
            .fulfillmentStatus(DEFAULT_FULFILLMENT_STATUS)
            .gateway(DEFAULT_GATEWAY)
            .ward(DEFAULT_WARD)
            .street(DEFAULT_STREET)
            .fulfillmentDate(DEFAULT_FULFILLMENT_DATE)
            .cancelledAt(DEFAULT_CANCELLED_AT)
            .cancelReason(DEFAULT_CANCEL_REASON);
        return order;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createUpdatedEntity(EntityManager em) {
        Order order = new Order()
            .orderCode(UPDATED_ORDER_CODE)
            .transId(UPDATED_TRANS_ID)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .totalDiscount(UPDATED_TOTAL_DISCOUNT)
            .point(UPDATED_POINT)
            .userId(UPDATED_USER_ID)
            .payMethod(UPDATED_PAY_METHOD)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .shippingStatus(UPDATED_SHIPPING_STATUS)
            .deliveryCode(UPDATED_DELIVERY_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .shopifyId(UPDATED_SHOPIFY_ID)
            .shopifyUserId(UPDATED_SHOPIFY_USER_ID)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .fulfillmentStatus(UPDATED_FULFILLMENT_STATUS)
            .gateway(UPDATED_GATEWAY)
            .ward(UPDATED_WARD)
            .street(UPDATED_STREET)
            .fulfillmentDate(UPDATED_FULFILLMENT_DATE)
            .cancelledAt(UPDATED_CANCELLED_AT)
            .cancelReason(UPDATED_CANCEL_REASON);
        return order;
    }

    @BeforeEach
    public void initTest() {
        order = createEntity(em);
    }

    @Test
    @Transactional
    void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();
        // Create the Order
        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderCode()).isEqualTo(DEFAULT_ORDER_CODE);
        assertThat(testOrder.getTransId()).isEqualTo(DEFAULT_TRANS_ID);
        assertThat(testOrder.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testOrder.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testOrder.getTotalDiscount()).isEqualTo(DEFAULT_TOTAL_DISCOUNT);
        assertThat(testOrder.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testOrder.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testOrder.getPayMethod()).isEqualTo(DEFAULT_PAY_METHOD);
        assertThat(testOrder.getShippingDate()).isEqualTo(DEFAULT_SHIPPING_DATE);
        assertThat(testOrder.getShippingStatus()).isEqualTo(DEFAULT_SHIPPING_STATUS);
        assertThat(testOrder.getDeliveryCode()).isEqualTo(DEFAULT_DELIVERY_CODE);
        assertThat(testOrder.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrder.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOrder.getProvincial()).isEqualTo(DEFAULT_PROVINCIAL);
        assertThat(testOrder.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testOrder.getShopifyId()).isEqualTo(DEFAULT_SHOPIFY_ID);
        assertThat(testOrder.getShopifyUserId()).isEqualTo(DEFAULT_SHOPIFY_USER_ID);
        assertThat(testOrder.getTotalWeight()).isEqualTo(DEFAULT_TOTAL_WEIGHT);
        assertThat(testOrder.getFulfillmentStatus()).isEqualTo(DEFAULT_FULFILLMENT_STATUS);
        assertThat(testOrder.getGateway()).isEqualTo(DEFAULT_GATEWAY);
        assertThat(testOrder.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testOrder.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testOrder.getFulfillmentDate()).isEqualTo(DEFAULT_FULFILLMENT_DATE);
        assertThat(testOrder.getCancelledAt()).isEqualTo(DEFAULT_CANCELLED_AT);
        assertThat(testOrder.getCancelReason()).isEqualTo(DEFAULT_CANCEL_REASON);
    }

    @Test
    @Transactional
    void createOrderWithExistingId() throws Exception {
        // Create the Order with an existing ID
        order.setId(1L);

        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOrderCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setOrderCode(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPaymentStatus(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setTotalAmount(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setTotalDiscount(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPoint(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPayMethodIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPayMethod(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkShippingStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setShippingStatus(null);

        // Create the Order, which fails.

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get all the orderList
        restOrderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderCode").value(hasItem(DEFAULT_ORDER_CODE)))
            .andExpect(jsonPath("$.[*].transId").value(hasItem(DEFAULT_TRANS_ID)))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS)))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalDiscount").value(hasItem(DEFAULT_TOTAL_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].payMethod").value(hasItem(DEFAULT_PAY_METHOD)))
            .andExpect(jsonPath("$.[*].shippingDate").value(hasItem(DEFAULT_SHIPPING_DATE.toString())))
            .andExpect(jsonPath("$.[*].shippingStatus").value(hasItem(DEFAULT_SHIPPING_STATUS)))
            .andExpect(jsonPath("$.[*].deliveryCode").value(hasItem(DEFAULT_DELIVERY_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].provincial").value(hasItem(DEFAULT_PROVINCIAL)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].shopifyId").value(hasItem(DEFAULT_SHOPIFY_ID.intValue())))
            .andExpect(jsonPath("$.[*].shopifyUserId").value(hasItem(DEFAULT_SHOPIFY_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].totalWeight").value(hasItem(DEFAULT_TOTAL_WEIGHT)))
            .andExpect(jsonPath("$.[*].fulfillmentStatus").value(hasItem(DEFAULT_FULFILLMENT_STATUS)))
            .andExpect(jsonPath("$.[*].gateway").value(hasItem(DEFAULT_GATEWAY)))
            .andExpect(jsonPath("$.[*].ward").value(hasItem(DEFAULT_WARD)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].fulfillmentDate").value(hasItem(DEFAULT_FULFILLMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].cancelledAt").value(hasItem(DEFAULT_CANCELLED_AT.toString())))
            .andExpect(jsonPath("$.[*].cancelReason").value(hasItem(DEFAULT_CANCEL_REASON)));
    }

    @Test
    @Transactional
    void getOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get the order
        restOrderMockMvc
            .perform(get(ENTITY_API_URL_ID, order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(order.getId().intValue()))
            .andExpect(jsonPath("$.orderCode").value(DEFAULT_ORDER_CODE))
            .andExpect(jsonPath("$.transId").value(DEFAULT_TRANS_ID))
            .andExpect(jsonPath("$.paymentStatus").value(DEFAULT_PAYMENT_STATUS))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalDiscount").value(DEFAULT_TOTAL_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.payMethod").value(DEFAULT_PAY_METHOD))
            .andExpect(jsonPath("$.shippingDate").value(DEFAULT_SHIPPING_DATE.toString()))
            .andExpect(jsonPath("$.shippingStatus").value(DEFAULT_SHIPPING_STATUS))
            .andExpect(jsonPath("$.deliveryCode").value(DEFAULT_DELIVERY_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.provincial").value(DEFAULT_PROVINCIAL))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.shopifyId").value(DEFAULT_SHOPIFY_ID.intValue()))
            .andExpect(jsonPath("$.shopifyUserId").value(DEFAULT_SHOPIFY_USER_ID.intValue()))
            .andExpect(jsonPath("$.totalWeight").value(DEFAULT_TOTAL_WEIGHT))
            .andExpect(jsonPath("$.fulfillmentStatus").value(DEFAULT_FULFILLMENT_STATUS))
            .andExpect(jsonPath("$.gateway").value(DEFAULT_GATEWAY))
            .andExpect(jsonPath("$.ward").value(DEFAULT_WARD))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.fulfillmentDate").value(DEFAULT_FULFILLMENT_DATE.toString()))
            .andExpect(jsonPath("$.cancelledAt").value(DEFAULT_CANCELLED_AT.toString()))
            .andExpect(jsonPath("$.cancelReason").value(DEFAULT_CANCEL_REASON));
    }

    @Test
    @Transactional
    void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
        em.detach(updatedOrder);
        updatedOrder
            .orderCode(UPDATED_ORDER_CODE)
            .transId(UPDATED_TRANS_ID)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .totalDiscount(UPDATED_TOTAL_DISCOUNT)
            .point(UPDATED_POINT)
            .userId(UPDATED_USER_ID)
            .payMethod(UPDATED_PAY_METHOD)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .shippingStatus(UPDATED_SHIPPING_STATUS)
            .deliveryCode(UPDATED_DELIVERY_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .shopifyId(UPDATED_SHOPIFY_ID)
            .shopifyUserId(UPDATED_SHOPIFY_USER_ID)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .fulfillmentStatus(UPDATED_FULFILLMENT_STATUS)
            .gateway(UPDATED_GATEWAY)
            .ward(UPDATED_WARD)
            .street(UPDATED_STREET)
            .fulfillmentDate(UPDATED_FULFILLMENT_DATE)
            .cancelledAt(UPDATED_CANCELLED_AT)
            .cancelReason(UPDATED_CANCEL_REASON);

        restOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOrder.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOrder))
            )
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderCode()).isEqualTo(UPDATED_ORDER_CODE);
        assertThat(testOrder.getTransId()).isEqualTo(UPDATED_TRANS_ID);
        assertThat(testOrder.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testOrder.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrder.getTotalDiscount()).isEqualTo(UPDATED_TOTAL_DISCOUNT);
        assertThat(testOrder.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testOrder.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testOrder.getPayMethod()).isEqualTo(UPDATED_PAY_METHOD);
        assertThat(testOrder.getShippingDate()).isEqualTo(UPDATED_SHIPPING_DATE);
        assertThat(testOrder.getShippingStatus()).isEqualTo(UPDATED_SHIPPING_STATUS);
        assertThat(testOrder.getDeliveryCode()).isEqualTo(UPDATED_DELIVERY_CODE);
        assertThat(testOrder.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrder.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrder.getProvincial()).isEqualTo(UPDATED_PROVINCIAL);
        assertThat(testOrder.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testOrder.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
        assertThat(testOrder.getShopifyUserId()).isEqualTo(UPDATED_SHOPIFY_USER_ID);
        assertThat(testOrder.getTotalWeight()).isEqualTo(UPDATED_TOTAL_WEIGHT);
        assertThat(testOrder.getFulfillmentStatus()).isEqualTo(UPDATED_FULFILLMENT_STATUS);
        assertThat(testOrder.getGateway()).isEqualTo(UPDATED_GATEWAY);
        assertThat(testOrder.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testOrder.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testOrder.getFulfillmentDate()).isEqualTo(UPDATED_FULFILLMENT_DATE);
        assertThat(testOrder.getCancelledAt()).isEqualTo(UPDATED_CANCELLED_AT);
        assertThat(testOrder.getCancelReason()).isEqualTo(UPDATED_CANCEL_REASON);
    }

    @Test
    @Transactional
    void putNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
        order.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, order.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(order))
            )
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
        order.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(order))
            )
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
        order.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderWithPatch() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order using partial update
        Order partialUpdatedOrder = new Order();
        partialUpdatedOrder.setId(order.getId());

        partialUpdatedOrder
            .orderCode(UPDATED_ORDER_CODE)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .totalDiscount(UPDATED_TOTAL_DISCOUNT)
            .point(UPDATED_POINT)
            .userId(UPDATED_USER_ID)
            .payMethod(UPDATED_PAY_METHOD)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .shippingStatus(UPDATED_SHIPPING_STATUS)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .fulfillmentStatus(UPDATED_FULFILLMENT_STATUS)
            .street(UPDATED_STREET)
            .cancelledAt(UPDATED_CANCELLED_AT)
            .cancelReason(UPDATED_CANCEL_REASON);

        restOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrder))
            )
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderCode()).isEqualTo(UPDATED_ORDER_CODE);
        assertThat(testOrder.getTransId()).isEqualTo(DEFAULT_TRANS_ID);
        assertThat(testOrder.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testOrder.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrder.getTotalDiscount()).isEqualTo(UPDATED_TOTAL_DISCOUNT);
        assertThat(testOrder.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testOrder.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testOrder.getPayMethod()).isEqualTo(UPDATED_PAY_METHOD);
        assertThat(testOrder.getShippingDate()).isEqualTo(UPDATED_SHIPPING_DATE);
        assertThat(testOrder.getShippingStatus()).isEqualTo(UPDATED_SHIPPING_STATUS);
        assertThat(testOrder.getDeliveryCode()).isEqualTo(DEFAULT_DELIVERY_CODE);
        assertThat(testOrder.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrder.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrder.getProvincial()).isEqualTo(DEFAULT_PROVINCIAL);
        assertThat(testOrder.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testOrder.getShopifyId()).isEqualTo(DEFAULT_SHOPIFY_ID);
        assertThat(testOrder.getShopifyUserId()).isEqualTo(DEFAULT_SHOPIFY_USER_ID);
        assertThat(testOrder.getTotalWeight()).isEqualTo(UPDATED_TOTAL_WEIGHT);
        assertThat(testOrder.getFulfillmentStatus()).isEqualTo(UPDATED_FULFILLMENT_STATUS);
        assertThat(testOrder.getGateway()).isEqualTo(DEFAULT_GATEWAY);
        assertThat(testOrder.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testOrder.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testOrder.getFulfillmentDate()).isEqualTo(DEFAULT_FULFILLMENT_DATE);
        assertThat(testOrder.getCancelledAt()).isEqualTo(UPDATED_CANCELLED_AT);
        assertThat(testOrder.getCancelReason()).isEqualTo(UPDATED_CANCEL_REASON);
    }

    @Test
    @Transactional
    void fullUpdateOrderWithPatch() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order using partial update
        Order partialUpdatedOrder = new Order();
        partialUpdatedOrder.setId(order.getId());

        partialUpdatedOrder
            .orderCode(UPDATED_ORDER_CODE)
            .transId(UPDATED_TRANS_ID)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .totalDiscount(UPDATED_TOTAL_DISCOUNT)
            .point(UPDATED_POINT)
            .userId(UPDATED_USER_ID)
            .payMethod(UPDATED_PAY_METHOD)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .shippingStatus(UPDATED_SHIPPING_STATUS)
            .deliveryCode(UPDATED_DELIVERY_CODE)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .provincial(UPDATED_PROVINCIAL)
            .district(UPDATED_DISTRICT)
            .shopifyId(UPDATED_SHOPIFY_ID)
            .shopifyUserId(UPDATED_SHOPIFY_USER_ID)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .fulfillmentStatus(UPDATED_FULFILLMENT_STATUS)
            .gateway(UPDATED_GATEWAY)
            .ward(UPDATED_WARD)
            .street(UPDATED_STREET)
            .fulfillmentDate(UPDATED_FULFILLMENT_DATE)
            .cancelledAt(UPDATED_CANCELLED_AT)
            .cancelReason(UPDATED_CANCEL_REASON);

        restOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrder))
            )
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderCode()).isEqualTo(UPDATED_ORDER_CODE);
        assertThat(testOrder.getTransId()).isEqualTo(UPDATED_TRANS_ID);
        assertThat(testOrder.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testOrder.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrder.getTotalDiscount()).isEqualTo(UPDATED_TOTAL_DISCOUNT);
        assertThat(testOrder.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testOrder.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testOrder.getPayMethod()).isEqualTo(UPDATED_PAY_METHOD);
        assertThat(testOrder.getShippingDate()).isEqualTo(UPDATED_SHIPPING_DATE);
        assertThat(testOrder.getShippingStatus()).isEqualTo(UPDATED_SHIPPING_STATUS);
        assertThat(testOrder.getDeliveryCode()).isEqualTo(UPDATED_DELIVERY_CODE);
        assertThat(testOrder.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrder.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrder.getProvincial()).isEqualTo(UPDATED_PROVINCIAL);
        assertThat(testOrder.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testOrder.getShopifyId()).isEqualTo(UPDATED_SHOPIFY_ID);
        assertThat(testOrder.getShopifyUserId()).isEqualTo(UPDATED_SHOPIFY_USER_ID);
        assertThat(testOrder.getTotalWeight()).isEqualTo(UPDATED_TOTAL_WEIGHT);
        assertThat(testOrder.getFulfillmentStatus()).isEqualTo(UPDATED_FULFILLMENT_STATUS);
        assertThat(testOrder.getGateway()).isEqualTo(UPDATED_GATEWAY);
        assertThat(testOrder.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testOrder.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testOrder.getFulfillmentDate()).isEqualTo(UPDATED_FULFILLMENT_DATE);
        assertThat(testOrder.getCancelledAt()).isEqualTo(UPDATED_CANCELLED_AT);
        assertThat(testOrder.getCancelReason()).isEqualTo(UPDATED_CANCEL_REASON);
    }

    @Test
    @Transactional
    void patchNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
        order.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, order.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(order))
            )
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
        order.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(order))
            )
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
        order.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc
            .perform(delete(ENTITY_API_URL_ID, order.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
