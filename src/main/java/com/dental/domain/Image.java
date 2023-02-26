package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Image.
 */
@Entity
@Table(name = "images")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "src", nullable = false)
    private String src;

    @Column(name = "shopify_id")
    private Long shopifyId;

    @Column(name = "img_public_id")
    private String imgPublicId;

    @NotNull
    @Column(name = "is_avatar", nullable = false)
    private Integer isAvatar;

    @Column(name = "combo_id")
    private Long comboId;

    @Column(name = "product_id")
    private Long productId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Image id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrc() {
        return this.src;
    }

    public Image src(String src) {
        this.setSrc(src);
        return this;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Long getShopifyId() {
        return this.shopifyId;
    }

    public Image shopifyId(Long shopifyId) {
        this.setShopifyId(shopifyId);
        return this;
    }

    public void setShopifyId(Long shopifyId) {
        this.shopifyId = shopifyId;
    }

    public String getImgPublicId() {
        return this.imgPublicId;
    }

    public Image imgPublicId(String imgPublicId) {
        this.setImgPublicId(imgPublicId);
        return this;
    }

    public void setImgPublicId(String imgPublicId) {
        this.imgPublicId = imgPublicId;
    }

    public Integer getIsAvatar() {
        return this.isAvatar;
    }

    public Image isAvatar(Integer isAvatar) {
        this.setIsAvatar(isAvatar);
        return this;
    }

    public void setIsAvatar(Integer isAvatar) {
        this.isAvatar = isAvatar;
    }

    public Long getComboId() {
        return this.comboId;
    }

    public Image comboId(Long comboId) {
        this.setComboId(comboId);
        return this;
    }

    public void setComboId(Long comboId) {
        this.comboId = comboId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public Image productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        return id != null && id.equals(((Image) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", src='" + getSrc() + "'" +
            ", shopifyId=" + getShopifyId() +
            ", imgPublicId='" + getImgPublicId() + "'" +
            ", isAvatar=" + getIsAvatar() +
            ", comboId=" + getComboId() +
            ", productId=" + getProductId() +
            "}";
    }
}
