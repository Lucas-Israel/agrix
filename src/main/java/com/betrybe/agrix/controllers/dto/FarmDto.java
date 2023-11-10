package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entity.Farm;

/**
 * Farm Data transfer object.
 *
 * @param id for farm.
 * @param name for farm.
 * @param size for farm.
 */
public record FarmDto(Long id, String name, Double size) {}
