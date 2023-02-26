package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Rank.
 */
@Entity
@Table(name = "ranks")
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "turnover_condition", nullable = false)
    private Double turnoverCondition;

    @NotNull
    @Column(name = "discount", nullable = false)
    private Double discount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Rank id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Rank name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTurnoverCondition() {
        return this.turnoverCondition;
    }

    public Rank turnoverCondition(Double turnoverCondition) {
        this.setTurnoverCondition(turnoverCondition);
        return this;
    }

    public void setTurnoverCondition(Double turnoverCondition) {
        this.turnoverCondition = turnoverCondition;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public Rank discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rank)) {
            return false;
        }
        return id != null && id.equals(((Rank) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rank{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", turnoverCondition=" + getTurnoverCondition() +
            ", discount=" + getDiscount() +
            "}";
    }
}
