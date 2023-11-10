package com.betrybe.agrix.controllers.dto;

/**
 * Token data transfer object, so the return is {token: "Token String" }.
 *
 * @param token to be sent.
 */
public record TokenDto(String token) {

}
