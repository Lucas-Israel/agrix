package com.betrybe.agrix.controllers.dto;

/**
 * Authentication data transfer object used for authenticatin users.
 *
 * @param username from user.
 * @param password from user.
 */
public record AuthenticationDto(String username, String password){
}
