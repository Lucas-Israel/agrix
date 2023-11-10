package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Fertilizer repository.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
