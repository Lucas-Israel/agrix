package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm service layer.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  // ----- Methods -----

  // *** Farm ***

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Search a farm based on its ID, if not found throws an exception.
   *
   * @param id to be searched.
   * @return a farm if found.
   */
  public Farm getFarmById(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    return optionalFarm.get();
  }

  // *** Farm ***
  // *** Crop ***

  /**
   * Creates a crop inside a farm, if a farm is not found thorws an exception.
   *
   * @param farmId to be searched.
   * @param crop to be planted.
   * @return the planted crop if a farm is found.
   */
  public Crop insertCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    Farm farm = optionalFarm.get();
    crop.setFarm(farm);
    Crop cropFromDb = cropRepository.save(crop);
    farm.setCrops(cropFromDb);

    return cropFromDb;
  }

  /**
   * Searches for all crops of a certain farm, if a farm is not found throws an exception.
   *
   * @param farmId to be searched.
   * @return a list of all crops said farm has.
   */
  public List<Crop> getAllCropsInFarm(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    return optionalFarm.get().getCrops();
  }
  // *** Crop ***

  // ----- Methods -----

}
