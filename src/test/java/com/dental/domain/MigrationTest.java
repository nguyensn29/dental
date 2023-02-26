package com.dental.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dental.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MigrationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Migration.class);
        Migration migration1 = new Migration();
        migration1.setId(1L);
        Migration migration2 = new Migration();
        migration2.setId(migration1.getId());
        assertThat(migration1).isEqualTo(migration2);
        migration2.setId(2L);
        assertThat(migration1).isNotEqualTo(migration2);
        migration1.setId(null);
        assertThat(migration1).isNotEqualTo(migration2);
    }
}
