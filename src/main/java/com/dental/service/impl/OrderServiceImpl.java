package com.dental.service.impl;

import com.dental.domain.Order;
import com.dental.repository.OrderRepository;
import com.dental.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        log.debug("Request to save Order : {}", order);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        log.debug("Request to save Order : {}", order);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> partialUpdate(Order order) {
        log.debug("Request to partially update Order : {}", order);

        return orderRepository
            .findById(order.getId())
            .map(existingOrder -> {
                if (order.getOrderCode() != null) {
                    existingOrder.setOrderCode(order.getOrderCode());
                }
                if (order.getTransId() != null) {
                    existingOrder.setTransId(order.getTransId());
                }
                if (order.getPaymentStatus() != null) {
                    existingOrder.setPaymentStatus(order.getPaymentStatus());
                }
                if (order.getTotalAmount() != null) {
                    existingOrder.setTotalAmount(order.getTotalAmount());
                }
                if (order.getTotalDiscount() != null) {
                    existingOrder.setTotalDiscount(order.getTotalDiscount());
                }
                if (order.getPoint() != null) {
                    existingOrder.setPoint(order.getPoint());
                }
                if (order.getUserId() != null) {
                    existingOrder.setUserId(order.getUserId());
                }
                if (order.getPayMethod() != null) {
                    existingOrder.setPayMethod(order.getPayMethod());
                }
                if (order.getShippingDate() != null) {
                    existingOrder.setShippingDate(order.getShippingDate());
                }
                if (order.getShippingStatus() != null) {
                    existingOrder.setShippingStatus(order.getShippingStatus());
                }
                if (order.getDeliveryCode() != null) {
                    existingOrder.setDeliveryCode(order.getDeliveryCode());
                }
                if (order.getName() != null) {
                    existingOrder.setName(order.getName());
                }
                if (order.getPhone() != null) {
                    existingOrder.setPhone(order.getPhone());
                }
                if (order.getAddress() != null) {
                    existingOrder.setAddress(order.getAddress());
                }
                if (order.getProvincial() != null) {
                    existingOrder.setProvincial(order.getProvincial());
                }
                if (order.getDistrict() != null) {
                    existingOrder.setDistrict(order.getDistrict());
                }
                if (order.getShopifyId() != null) {
                    existingOrder.setShopifyId(order.getShopifyId());
                }
                if (order.getShopifyUserId() != null) {
                    existingOrder.setShopifyUserId(order.getShopifyUserId());
                }
                if (order.getTotalWeight() != null) {
                    existingOrder.setTotalWeight(order.getTotalWeight());
                }
                if (order.getFulfillmentStatus() != null) {
                    existingOrder.setFulfillmentStatus(order.getFulfillmentStatus());
                }
                if (order.getGateway() != null) {
                    existingOrder.setGateway(order.getGateway());
                }
                if (order.getWard() != null) {
                    existingOrder.setWard(order.getWard());
                }
                if (order.getStreet() != null) {
                    existingOrder.setStreet(order.getStreet());
                }
                if (order.getFulfillmentDate() != null) {
                    existingOrder.setFulfillmentDate(order.getFulfillmentDate());
                }
                if (order.getCancelledAt() != null) {
                    existingOrder.setCancelledAt(order.getCancelledAt());
                }
                if (order.getCancelReason() != null) {
                    existingOrder.setCancelReason(order.getCancelReason());
                }

                return existingOrder;
            })
            .map(orderRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        log.debug("Request to get all Orders");
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }
}
