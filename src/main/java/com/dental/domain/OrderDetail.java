package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A OrderDetails.
 */
@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "product_type", nullable = false)
    private Integer productType;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @NotNull
    @Column(name = "qty", nullable = false)
    private Integer qty;

    @NotNull
    @Column(name = "discount", nullable = false)
    private Double discount;

    @NotNull
    @Column(name = "subtotal_amount", nullable = false)
    private Double subtotalAmount;

    @NotNull
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "variant_id")
    private Double variantId;

    @Column(name = "shopify_variant_id")
    private Long shopifyVariantId;

    @Column(name = "shopify_product_id")
    private Long shopifyProductId;

    @Column(name = "shopify_order_id")
    private Long shopifyOrderId;

    @Column(name = "shopify_id")
    private Long shopifyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderDetail id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public OrderDetail orderId(Integer orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public OrderDetail productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductType() {
        return this.productType;
    }

    public OrderDetail productType(Integer productType) {
        this.setProductType(productType);
        return this;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public OrderDetail avatar(String avatar) {
        this.setAvatar(avatar);
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return this.name;
    }

    public OrderDetail name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public OrderDetail price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPoint() {
        return this.point;
    }

    public OrderDetail point(Integer point) {
        this.setPoint(point);
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getQty() {
        return this.qty;
    }

    public OrderDetail qty(Integer qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public OrderDetail discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSubtotalAmount() {
        return this.subtotalAmount;
    }

    public OrderDetail subtotalAmount(Double subtotalAmount) {
        this.setSubtotalAmount(subtotalAmount);
        return this;
    }

    public void setSubtotalAmount(Double subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public OrderDetail totalAmount(Double totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getVariantId() {
        return this.variantId;
    }

    public OrderDetail variantId(Double variantId) {
        this.setVariantId(variantId);
        return this;
    }

    public void setVariantId(Double variantId) {
        this.variantId = variantId;
    }

    public Long getShopifyVariantId() {
        return this.shopifyVariantId;
    }

    public OrderDetail shopifyVariantId(Long shopifyVariantId) {
        this.setShopifyVariantId(shopifyVariantId);
        return this;
    }

    public void setShopifyVariantId(Long shopifyVariantId) {
        this.shopifyVariantId = shopifyVariantId;
    }

    public Long getShopifyProductId() {
        return this.shopifyProductId;
    }

    public OrderDetail shopifyProductId(Long shopifyProductId) {
        this.setShopifyProductId(shopifyProductId);
        return this;
    }

    public void setShopifyProductId(Long shopifyProductId) {
        this.shopifyProductId = shopifyProductId;
    }

    public Long getShopifyOrderId() {
        return this.shopifyOrderId;
    }

    public OrderDetail shopifyOrderId(Long shopifyOrderId) {
        this.setShopifyOrderId(shopifyOrderId);
        return this;
    }

    public void setShopifyOrderId(Long shopifyOrderId) {
        this.shopifyOrderId = shopifyOrderId;
    }

    public Long getShopifyId() {
        return this.shopifyId;
    }

    public OrderDetail shopifyId(Long shopifyId) {
        this.setShopifyId(shopifyId);
        return this;
    }

    public void setShopifyId(Long shopifyId) {
        this.shopifyId = shopifyId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDetail)) {
            return false;
        }
        return id != null && id.equals(((OrderDetail) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDetails{" +
            "id=" + getId() +
            ", orderId=" + getOrderId() +
            ", productId=" + getProductId() +
            ", productType=" + getProductType() +
            ", avatar='" + getAvatar() + "'" +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", point=" + getPoint() +
            ", qty=" + getQty() +
            ", discount=" + getDiscount() +
            ", subtotalAmount=" + getSubtotalAmount() +
            ", totalAmount=" + getTotalAmount() +
            ", variantId=" + getVariantId() +
            ", shopifyVariantId=" + getShopifyVariantId() +
            ", shopifyProductId=" + getShopifyProductId() +
            ", shopifyOrderId=" + getShopifyOrderId() +
            ", shopifyId=" + getShopifyId() +
            "}";
    }
}
