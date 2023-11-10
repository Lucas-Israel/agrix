package com.betrybe.agrix.controllers.dto;

/**
 * Fertilizer Data transfer object.
 *
 * @param id of fertilizer.
 * @param name of fertilizer.
 * @param brand of fertilizer.
 * @param composition of fertilizer.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

}
