package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller advice to concetrate exception handling.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> hanglerNotFound(NotFoundException notFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
  }
}
