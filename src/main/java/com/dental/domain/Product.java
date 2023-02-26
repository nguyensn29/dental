package com.dental.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * The Employee entity.
 */
@Schema(description = "The Employee entity.")
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The firstname attribute.
     */
    @Schema(description = "The firstname attribute.", required = true)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "liked", nullable = false)
    private Integer liked;

    @Column(name = "shopify_id")
    private Long shopifyId;

    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "is_combo")
    private Integer isCombo;

    @Column(name = "discount")
    private Double discount;

    @NotNull
    @Column(name = "bought", nullable = false)
    private Long bought;

    @Column(name = "price")
    private Double price;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return this.point;
    }

    public Product point(Integer point) {
        this.setPoint(point);
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLiked() {
        return this.liked;
    }

    public Product liked(Integer liked) {
        this.setLiked(liked);
        return this;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Long getShopifyId() {
        return this.shopifyId;
    }

    public Product shopifyId(Long shopifyId) {
        this.setShopifyId(shopifyId);
        return this;
    }

    public void setShopifyId(Long shopifyId) {
        this.shopifyId = shopifyId;
    }

    public Integer getVendorId() {
        return this.vendorId;
    }

    public Product vendorId(Integer vendorId) {
        this.setVendorId(vendorId);
        return this;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public Product categoryId(Integer categoryId) {
        this.setCategoryId(categoryId);
        return this;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIsCombo() {
        return this.isCombo;
    }

    public Product isCombo(Integer isCombo) {
        this.setIsCombo(isCombo);
        return this;
    }

    public void setIsCombo(Integer isCombo) {
        this.isCombo = isCombo;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public Product discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getBought() {
        return this.bought;
    }

    public Product bought(Long bought) {
        this.setBought(bought);
        return this;
    }

    public void setBought(Long bought) {
        this.bought = bought;
    }

    public Double getPrice() {
        return this.price;
    }

    public Product price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", point=" + getPoint() +
            ", description='" + getDescription() + "'" +
            ", liked=" + getLiked() +
            ", shopifyId=" + getShopifyId() +
            ", vendorId=" + getVendorId() +
            ", categoryId=" + getCategoryId() +
            ", isCombo=" + getIsCombo() +
            ", discount=" + getDiscount() +
            ", bought=" + getBought() +
            ", price=" + getPrice() +
            "}";
    }
}
