package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entity.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create a fertilizer.
   *
   * @param fertilizer to be created from the JSON body { name, brand, composition }
   * @return the created fertilizer.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> postFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerService.postFertilizer(fertilizer);
    FertilizerDto fertilizerDto = new FertilizerDto(newFertilizer.getId(), newFertilizer.getName(),
        newFertilizer.getBrand(), newFertilizer.getComposition());

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDto);
  }

  /**
   * Searches for all fertilizers.
   *
   * @return a list of fertilizers.
   */
  @GetMapping
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
        .map(fertilizer -> new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
            fertilizer.getBrand(), fertilizer.getComposition()))
        .toList();

    return ResponseEntity.ok(fertilizerDtos);
  }

  /**
   * Searches for a fertilizer based on its ID.
   *
   * @param fertilizerId from a fertilzier
   * @return a fertilizer.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    FertilizerDto fertilizerDto = new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition());

    return ResponseEntity.ok(fertilizerDto);
  }
}
