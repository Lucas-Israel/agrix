package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.security.Role;

/**
 * Person data transfer object.
 *
 * @param id of person.
 * @param username of person.
 * @param role of person.
 */
public record PersonDto(Long id, String username, Role role) {

}
