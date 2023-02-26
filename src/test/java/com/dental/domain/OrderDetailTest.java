package com.dental.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dental.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDetail.class);
        OrderDetail orderDetails1 = new OrderDetail();
        orderDetails1.setId(1L);
        OrderDetail orderDetails2 = new OrderDetail();
        orderDetails2.setId(orderDetails1.getId());
        assertThat(orderDetails1).isEqualTo(orderDetails2);
        orderDetails2.setId(2L);
        assertThat(orderDetails1).isNotEqualTo(orderDetails2);
        orderDetails1.setId(null);
        assertThat(orderDetails1).isNotEqualTo(orderDetails2);
    }
}
