package com.dental.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Migration.
 */
@Entity
@Table(name = "migrations")
public class Migration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "migration", nullable = false)
    private String migration;

    @NotNull
    @Column(name = "batch", nullable = false)
    private Integer batch;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Migration id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMigration() {
        return this.migration;
    }

    public Migration migration(String migration) {
        this.setMigration(migration);
        return this;
    }

    public void setMigration(String migration) {
        this.migration = migration;
    }

    public Integer getBatch() {
        return this.batch;
    }

    public Migration batch(Integer batch) {
        this.setBatch(batch);
        return this;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Migration)) {
            return false;
        }
        return id != null && id.equals(((Migration) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Migration{" +
            "id=" + getId() +
            ", migration='" + getMigration() + "'" +
            ", batch=" + getBatch() +
            "}";
    }
}
