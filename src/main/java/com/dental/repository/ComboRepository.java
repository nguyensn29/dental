package com.dental.repository;

import com.dental.domain.Combo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Combo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {}
