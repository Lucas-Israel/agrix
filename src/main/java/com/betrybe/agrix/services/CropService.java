package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Searches for all crops.
   *
   * @return a list of all crops.
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Searches for a crop by its id.
   *
   * @param id to be searched.
   * @return a crop, if it doesn't exist throw an exception.
   */
  public Crop findCropById(Long id) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }
    return optionalCrop.get();
  }

  /**
   * Searches for crops based when it was planted until its harvest.
   *
   * @param start when it was planted.
   * @param end its harvest date.
   * @return a list of crops.
   */
  public List<Crop> getCropsByDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Links a fertilizer to a crop and vice versa.
   *
   * @param cropId from a crop.
   * @param fertilizerId from a fertilizer.
   */
  public void linkFertilizerToCrop(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();
    crop.setFertilizers(fertilizer);
    cropRepository.save(crop);
    fertilizer.setCrops(crop);
    fertilizerRepository.save(fertilizer);
  }

  /**
   * Searches for fertilizers by crop id on crop repository.
   *
   * @param cropId to be searched.
   * @return a list of all, if any, fertilizer in a crop.
   */
  public List<Fertilizer> getFertilizersByCropId(Long cropId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }
    Crop crop = optionalCrop.get();

    return crop.getFertilizers();
  }
}
