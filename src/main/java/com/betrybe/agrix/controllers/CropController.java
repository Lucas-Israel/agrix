package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Searches for all crops.
   *
   * @return all crops in a list.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();
    List<CropDto> cropDtoList = crops.stream()
        .map(item -> new CropDto(item.getId(), item.getName(), item.getPlantedArea(),
            item.getFarm().getId(), item.getPlantedDate(), item.getHarvestDate()))
        .toList();
    return ResponseEntity.ok(cropDtoList);
  }

  /**
   * Searches a crop by its id.
   *
   * @param id to be searched.
   * @return a crop.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> findCropById(@PathVariable Long id) {
    Crop crop = cropService.findCropById(id);
    CropDto cropDto = new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate());

    return ResponseEntity.ok(cropDto);
  }

  /**
   * Searches a crop by date.
   *
   * @param start of when to search.
   * @param end of when to search.
   * @return a list of crops found by date.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> findCropByDate(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> crops = cropService.getCropsByDate(start, end);
    List<CropDto> cropDtos = crops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .toList();

    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Links a fertilizer to a crop and vice versa.
   *
   * @param cropId from a crop.
   * @param fertilizerId from a fertilizer.
   * @return a String confirming that it was linked.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> linkFertilizerToCrop(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    cropService.linkFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED).body(
        "Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Searches All fertilizers based on crop id.
   *
   * @param cropId of a crop
   * @return a list of fertilizers.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizersByCropId(cropId);
    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
        .map(fertilizer -> new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
            fertilizer.getBrand(), fertilizer.getComposition()))
        .toList();
    return ResponseEntity.ok(fertilizerDtos);
  }
}
