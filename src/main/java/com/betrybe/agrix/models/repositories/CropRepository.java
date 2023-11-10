package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
