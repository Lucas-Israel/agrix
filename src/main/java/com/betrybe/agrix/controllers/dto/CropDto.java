package com.betrybe.agrix.controllers.dto;


import java.time.LocalDate;

/**
 * Crop data transfer object.
 *
 * @param id for crop
 * @param name for crop
 * @param plantedArea for crop
 * @param farmId for farm
 */
public record CropDto(Long id, String name, Double plantedArea, Long farmId, LocalDate plantedDate,
                      LocalDate harvestDate) {}
