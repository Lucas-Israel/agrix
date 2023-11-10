package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.models.entity.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Insert a person into the database.
   *
   * @param person to be inserted.
   * @return PersonDTO
   */
  @PostMapping
  public ResponseEntity<PersonDto> insertPerson(@RequestBody Person person) {
    Person newPerson = personService.create(person);
    PersonDto personDto = new PersonDto(newPerson.getId(), newPerson.getUsername(),
        newPerson.getRole());
    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }
}
