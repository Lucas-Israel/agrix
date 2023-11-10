package com.betrybe.agrix.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceptions for elements not found inside the database.
 */
@ResponseStatus
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
