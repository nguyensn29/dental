package com.dental.repository;

import com.dental.domain.CollectionProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CollectionProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionProductRepository extends JpaRepository<CollectionProduct, Long> {}
