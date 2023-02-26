package com.dental.repository;

import com.dental.domain.ProductUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProductUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {}
