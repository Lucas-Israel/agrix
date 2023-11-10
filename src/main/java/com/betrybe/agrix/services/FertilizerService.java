package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entity.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Fertilizer service.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer postFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Searches for a fertilizer by id.
   *
   * @param id from a fertilizer.
   * @return said fertilizer.
   */
  public Fertilizer getFertilizerById(Long id) {
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(id);
    if (fertilizerOptional.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }
    return fertilizerOptional.get();
  }
}
