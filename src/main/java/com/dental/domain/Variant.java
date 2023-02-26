package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Variant.
 */
@Entity
@Table(name = "variants")
public class Variant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "shopify_id")
    private Long shopifyId;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "option_1")
    private String option1;

    @Column(name = "option_2")
    private String option2;

    @Column(name = "option_3")
    private String option3;

    @Column(name = "image_id")
    private Long imageId;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "compare_at_price")
    private Double compareAtPrice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Variant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public Variant productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getShopifyId() {
        return this.shopifyId;
    }

    public Variant shopifyId(Long shopifyId) {
        this.setShopifyId(shopifyId);
        return this;
    }

    public void setShopifyId(Long shopifyId) {
        this.shopifyId = shopifyId;
    }

    public String getTitle() {
        return this.title;
    }

    public Variant title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return this.price;
    }

    public Variant price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public Variant discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getOption1() {
        return this.option1;
    }

    public Variant option1(String option1) {
        this.setOption1(option1);
        return this;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return this.option2;
    }

    public Variant option2(String option2) {
        this.setOption2(option2);
        return this;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return this.option3;
    }

    public Variant option3(String option3) {
        this.setOption3(option3);
        return this;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public Long getImageId() {
        return this.imageId;
    }

    public Variant imageId(Long imageId) {
        this.setImageId(imageId);
        return this;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public Variant weight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getCompareAtPrice() {
        return this.compareAtPrice;
    }

    public Variant compareAtPrice(Double compareAtPrice) {
        this.setCompareAtPrice(compareAtPrice);
        return this;
    }

    public void setCompareAtPrice(Double compareAtPrice) {
        this.compareAtPrice = compareAtPrice;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Variant)) {
            return false;
        }
        return id != null && id.equals(((Variant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Variant{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", shopifyId=" + getShopifyId() +
            ", title='" + getTitle() + "'" +
            ", price=" + getPrice() +
            ", discount=" + getDiscount() +
            ", option1='" + getOption1() + "'" +
            ", option2='" + getOption2() + "'" +
            ", option3='" + getOption3() + "'" +
            ", imageId=" + getImageId() +
            ", weight=" + getWeight() +
            ", compareAtPrice=" + getCompareAtPrice() +
            "}";
    }
}
