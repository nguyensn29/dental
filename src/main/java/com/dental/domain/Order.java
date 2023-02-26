package com.dental.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Order.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "order_code", nullable = false)
    private String orderCode;

    @Column(name = "trans_id")
    private String transId;

    @NotNull
    @Column(name = "payment_status", nullable = false)
    private Integer paymentStatus;

    @NotNull
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @NotNull
    @Column(name = "total_discount", nullable = false)
    private Double totalDiscount;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "pay_method", nullable = false)
    private Integer payMethod;

    @Column(name = "shipping_date")
    private Instant shippingDate;

    @NotNull
    @Column(name = "shipping_status", nullable = false)
    private Integer shippingStatus;

    @Column(name = "delivery_code")
    private String deliveryCode;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "provincial")
    private String provincial;

    @Column(name = "district")
    private String district;

    @Column(name = "shopify_id")
    private Long shopifyId;

    @Column(name = "shopify_user_id")
    private Long shopifyUserId;

    @Column(name = "total_weight")
    private Integer totalWeight;

    @Column(name = "fulfillment_status")
    private String fulfillmentStatus;

    @Column(name = "gateway")
    private String gateway;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "fulfillment_date")
    private Instant fulfillmentDate;

    @Column(name = "cancelled_at")
    private Instant cancelledAt;

    @Column(name = "cancel_reason")
    private String cancelReason;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Order id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public Order orderCode(String orderCode) {
        this.setOrderCode(orderCode);
        return this;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTransId() {
        return this.transId;
    }

    public Order transId(String transId) {
        this.setTransId(transId);
        return this;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Integer getPaymentStatus() {
        return this.paymentStatus;
    }

    public Order paymentStatus(Integer paymentStatus) {
        this.setPaymentStatus(paymentStatus);
        return this;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public Order totalAmount(Double totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDiscount() {
        return this.totalDiscount;
    }

    public Order totalDiscount(Double totalDiscount) {
        this.setTotalDiscount(totalDiscount);
        return this;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Integer getPoint() {
        return this.point;
    }

    public Order point(Integer point) {
        this.setPoint(point);
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Order userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPayMethod() {
        return this.payMethod;
    }

    public Order payMethod(Integer payMethod) {
        this.setPayMethod(payMethod);
        return this;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Instant getShippingDate() {
        return this.shippingDate;
    }

    public Order shippingDate(Instant shippingDate) {
        this.setShippingDate(shippingDate);
        return this;
    }

    public void setShippingDate(Instant shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Integer getShippingStatus() {
        return this.shippingStatus;
    }

    public Order shippingStatus(Integer shippingStatus) {
        this.setShippingStatus(shippingStatus);
        return this;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getDeliveryCode() {
        return this.deliveryCode;
    }

    public Order deliveryCode(String deliveryCode) {
        this.setDeliveryCode(deliveryCode);
        return this;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getName() {
        return this.name;
    }

    public Order name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public Order phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public Order address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvincial() {
        return this.provincial;
    }

    public Order provincial(String provincial) {
        this.setProvincial(provincial);
        return this;
    }

    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }

    public String getDistrict() {
        return this.district;
    }

    public Order district(String district) {
        this.setDistrict(district);
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getShopifyId() {
        return this.shopifyId;
    }

    public Order shopifyId(Long shopifyId) {
        this.setShopifyId(shopifyId);
        return this;
    }

    public void setShopifyId(Long shopifyId) {
        this.shopifyId = shopifyId;
    }

    public Long getShopifyUserId() {
        return this.shopifyUserId;
    }

    public Order shopifyUserId(Long shopifyUserId) {
        this.setShopifyUserId(shopifyUserId);
        return this;
    }

    public void setShopifyUserId(Long shopifyUserId) {
        this.shopifyUserId = shopifyUserId;
    }

    public Integer getTotalWeight() {
        return this.totalWeight;
    }

    public Order totalWeight(Integer totalWeight) {
        this.setTotalWeight(totalWeight);
        return this;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getFulfillmentStatus() {
        return this.fulfillmentStatus;
    }

    public Order fulfillmentStatus(String fulfillmentStatus) {
        this.setFulfillmentStatus(fulfillmentStatus);
        return this;
    }

    public void setFulfillmentStatus(String fulfillmentStatus) {
        this.fulfillmentStatus = fulfillmentStatus;
    }

    public String getGateway() {
        return this.gateway;
    }

    public Order gateway(String gateway) {
        this.setGateway(gateway);
        return this;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getWard() {
        return this.ward;
    }

    public Order ward(String ward) {
        this.setWard(ward);
        return this;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return this.street;
    }

    public Order street(String street) {
        this.setStreet(street);
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Instant getFulfillmentDate() {
        return this.fulfillmentDate;
    }

    public Order fulfillmentDate(Instant fulfillmentDate) {
        this.setFulfillmentDate(fulfillmentDate);
        return this;
    }

    public void setFulfillmentDate(Instant fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }

    public Instant getCancelledAt() {
        return this.cancelledAt;
    }

    public Order cancelledAt(Instant cancelledAt) {
        this.setCancelledAt(cancelledAt);
        return this;
    }

    public void setCancelledAt(Instant cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public Order cancelReason(String cancelReason) {
        this.setCancelReason(cancelReason);
        return this;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", orderCode='" + getOrderCode() + "'" +
            ", transId='" + getTransId() + "'" +
            ", paymentStatus=" + getPaymentStatus() +
            ", totalAmount=" + getTotalAmount() +
            ", totalDiscount=" + getTotalDiscount() +
            ", point=" + getPoint() +
            ", userId=" + getUserId() +
            ", payMethod=" + getPayMethod() +
            ", shippingDate='" + getShippingDate() + "'" +
            ", shippingStatus=" + getShippingStatus() +
            ", deliveryCode='" + getDeliveryCode() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", provincial='" + getProvincial() + "'" +
            ", district='" + getDistrict() + "'" +
            ", shopifyId=" + getShopifyId() +
            ", shopifyUserId=" + getShopifyUserId() +
            ", totalWeight=" + getTotalWeight() +
            ", fulfillmentStatus='" + getFulfillmentStatus() + "'" +
            ", gateway='" + getGateway() + "'" +
            ", ward='" + getWard() + "'" +
            ", street='" + getStreet() + "'" +
            ", fulfillmentDate='" + getFulfillmentDate() + "'" +
            ", cancelledAt='" + getCancelledAt() + "'" +
            ", cancelReason='" + getCancelReason() + "'" +
            "}";
    }
}
