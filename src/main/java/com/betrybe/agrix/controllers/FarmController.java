package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Creates a farm.
   *
   * @param farmBody to be created.
   * @return the created farm.
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody Farm farmBody) {
    Farm newFarm = farmService.insertFarm(farmBody);
    FarmDto farmDto = new FarmDto(newFarm.getId(), newFarm.getName(), newFarm.getSize());

    return ResponseEntity.status(HttpStatus.CREATED).body(farmDto);
  }

  /**
   * Search all farms.
   *
   * @return a list of farms.
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> farmList = farmService.getAllFarms();
    List<FarmDto> farmDtoList = farmList.stream()
        .map(item -> new FarmDto(item.getId(), item.getName(), item.getSize()))
        .toList();
    return ResponseEntity.ok(farmDtoList);
  }

  /**
   * Search farm based on its ID.
   *
   * @param farmId for farm.
   * @return a farm if its found.
   */
  @GetMapping("/{farmId}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return ResponseEntity.ok(farmDto);
  }

  /**
   * Creates a crop inside a farm.
   *
   * @param farmId of the farm.
   * @param cropBody that will be planted.
   * @return crop information.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> insertCrop(@PathVariable Long farmId, @RequestBody Crop cropBody) {
    Crop crop = farmService.insertCrop(farmId, cropBody);
    CropDto cropDto = new CropDto(crop.getId(), crop.getName(), cropBody.getPlantedArea(),
        crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate());
    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * Search for all crops in a farm.
   *
   * @param farmId to be searched.
   * @return a list of crops in the farm.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllCropsInFarm(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getAllCropsInFarm(farmId);
    List<CropDto> cropDtoList = crops.stream()
        .map(item -> new CropDto(item.getId(), item.getName(), item.getPlantedArea(),
            item.getFarm().getId(), item.getPlantedDate(), item.getHarvestDate()))
        .toList();
    return ResponseEntity.ok(cropDtoList);
  }
}
