package com.dental.repository;

import com.dental.domain.OrderDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {}
