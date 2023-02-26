package com.dental.service.impl;

import com.dental.domain.OrderDetails;
import com.dental.repository.OrderDetailsRepository;
import com.dental.service.OrderDetailsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderDetails}.
 */
@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final Logger log = LoggerFactory.getLogger(OrderDetailsServiceImpl.class);

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        log.debug("Request to save OrderDetails : {}", orderDetails);
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails update(OrderDetails orderDetails) {
        log.debug("Request to save OrderDetails : {}", orderDetails);
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public Optional<OrderDetails> partialUpdate(OrderDetails orderDetails) {
        log.debug("Request to partially update OrderDetails : {}", orderDetails);

        return orderDetailsRepository
            .findById(orderDetails.getId())
            .map(existingOrderDetails -> {
                if (orderDetails.getOrderId() != null) {
                    existingOrderDetails.setOrderId(orderDetails.getOrderId());
                }
                if (orderDetails.getProductId() != null) {
                    existingOrderDetails.setProductId(orderDetails.getProductId());
                }
                if (orderDetails.getProductType() != null) {
                    existingOrderDetails.setProductType(orderDetails.getProductType());
                }
                if (orderDetails.getAvatar() != null) {
                    existingOrderDetails.setAvatar(orderDetails.getAvatar());
                }
                if (orderDetails.getName() != null) {
                    existingOrderDetails.setName(orderDetails.getName());
                }
                if (orderDetails.getPrice() != null) {
                    existingOrderDetails.setPrice(orderDetails.getPrice());
                }
                if (orderDetails.getPoint() != null) {
                    existingOrderDetails.setPoint(orderDetails.getPoint());
                }
                if (orderDetails.getQty() != null) {
                    existingOrderDetails.setQty(orderDetails.getQty());
                }
                if (orderDetails.getDiscount() != null) {
                    existingOrderDetails.setDiscount(orderDetails.getDiscount());
                }
                if (orderDetails.getSubtotalAmount() != null) {
                    existingOrderDetails.setSubtotalAmount(orderDetails.getSubtotalAmount());
                }
                if (orderDetails.getTotalAmount() != null) {
                    existingOrderDetails.setTotalAmount(orderDetails.getTotalAmount());
                }
                if (orderDetails.getVariantId() != null) {
                    existingOrderDetails.setVariantId(orderDetails.getVariantId());
                }
                if (orderDetails.getShopifyVariantId() != null) {
                    existingOrderDetails.setShopifyVariantId(orderDetails.getShopifyVariantId());
                }
                if (orderDetails.getShopifyProductId() != null) {
                    existingOrderDetails.setShopifyProductId(orderDetails.getShopifyProductId());
                }
                if (orderDetails.getShopifyOrderId() != null) {
                    existingOrderDetails.setShopifyOrderId(orderDetails.getShopifyOrderId());
                }
                if (orderDetails.getShopifyId() != null) {
                    existingOrderDetails.setShopifyId(orderDetails.getShopifyId());
                }

                return existingOrderDetails;
            })
            .map(orderDetailsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetails> findAll() {
        log.debug("Request to get all OrderDetails");
        return orderDetailsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDetails> findOne(Long id) {
        log.debug("Request to get OrderDetails : {}", id);
        return orderDetailsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderDetails : {}", id);
        orderDetailsRepository.deleteById(id);
    }
}
