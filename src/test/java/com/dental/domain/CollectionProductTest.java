package com.dental.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dental.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CollectionProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollectionProduct.class);
        CollectionProduct collectionProduct1 = new CollectionProduct();
        collectionProduct1.setId(1L);
        CollectionProduct collectionProduct2 = new CollectionProduct();
        collectionProduct2.setId(collectionProduct1.getId());
        assertThat(collectionProduct1).isEqualTo(collectionProduct2);
        collectionProduct2.setId(2L);
        assertThat(collectionProduct1).isNotEqualTo(collectionProduct2);
        collectionProduct1.setId(null);
        assertThat(collectionProduct1).isNotEqualTo(collectionProduct2);
    }
}
