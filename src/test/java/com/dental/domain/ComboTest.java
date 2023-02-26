package com.dental.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dental.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ComboTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Combo.class);
        Combo combo1 = new Combo();
        combo1.setId(1L);
        Combo combo2 = new Combo();
        combo2.setId(combo1.getId());
        assertThat(combo1).isEqualTo(combo2);
        combo2.setId(2L);
        assertThat(combo1).isNotEqualTo(combo2);
        combo1.setId(null);
        assertThat(combo1).isNotEqualTo(combo2);
    }
}
