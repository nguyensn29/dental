package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Banner.
 */
@Entity
@Table(name = "banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "src", nullable = false)
    private String src;

    @NotNull
    @Column(name = "is_show", nullable = false)
    private Integer isShow;

    @Column(name = "img_public_id")
    private String imgPublicId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Banner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Banner name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return this.src;
    }

    public Banner src(String src) {
        this.setSrc(src);
        return this;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public Banner isShow(Integer isShow) {
        this.setIsShow(isShow);
        return this;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getImgPublicId() {
        return this.imgPublicId;
    }

    public Banner imgPublicId(String imgPublicId) {
        this.setImgPublicId(imgPublicId);
        return this;
    }

    public void setImgPublicId(String imgPublicId) {
        this.imgPublicId = imgPublicId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Banner)) {
            return false;
        }
        return id != null && id.equals(((Banner) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Banner{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", src='" + getSrc() + "'" +
            ", isShow=" + getIsShow() +
            ", imgPublicId='" + getImgPublicId() + "'" +
            "}";
    }
}
