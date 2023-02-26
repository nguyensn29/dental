package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Setting.
 */
@Entity
@Table(name = "settings")
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "value")
    private String value;

    @Column(name = "rule")
    private String rule;

    @NotNull
    @Column(name = "is_number", nullable = false)
    private Integer isNumber;

    @NotNull
    @Column(name = "is_object", nullable = false)
    private Integer isObject;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Setting id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Setting name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public Setting keyName(String keyName) {
        this.setKeyName(keyName);
        return this;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return this.value;
    }

    public Setting value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRule() {
        return this.rule;
    }

    public Setting rule(String rule) {
        this.setRule(rule);
        return this;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getIsNumber() {
        return this.isNumber;
    }

    public Setting isNumber(Integer isNumber) {
        this.setIsNumber(isNumber);
        return this;
    }

    public void setIsNumber(Integer isNumber) {
        this.isNumber = isNumber;
    }

    public Integer getIsObject() {
        return this.isObject;
    }

    public Setting isObject(Integer isObject) {
        this.setIsObject(isObject);
        return this;
    }

    public void setIsObject(Integer isObject) {
        this.isObject = isObject;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Setting)) {
            return false;
        }
        return id != null && id.equals(((Setting) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Setting{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", keyName='" + getKeyName() + "'" +
            ", value='" + getValue() + "'" +
            ", rule='" + getRule() + "'" +
            ", isNumber=" + getIsNumber() +
            ", isObject=" + getIsObject() +
            "}";
    }
}
