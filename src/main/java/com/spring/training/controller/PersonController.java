package com.spring.training.controller;

import com.spring.training.domain.Person;
import com.spring.training.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("persons")
@AllArgsConstructor
public class PersonController {

    final PersonService personService;

    @GetMapping
    @Operation(summary = "getPersons", description = "return the list of persons")
    @ApiResponse(responseCode = "200", description = "persons found successfully")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("{id}")
    @Operation(summary = "getPerson", description = "return a person by its id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "person found successfully"),
            @ApiResponse(responseCode = "404", description = "person not found")})
    public Person getPerson(@Parameter(description = "person id", required = true) @PathVariable("id") Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "createPerson", description = "create a person")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "person created successfully"),
            @ApiResponse(responseCode = "404", description = "country not found")})
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("{id}")
    @Operation(summary = "updatePerson", description = "update a person by its id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "person updated successfully"),
            @ApiResponse(responseCode = "404", description = "person or country not found")})
    public Person updatePerson(@Parameter(description = "person id", required = true) @PathVariable("id") Long id,
                               @Valid @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "deletePerson", description = "delete a person by its id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "person deleted successfully"),
            @ApiResponse(responseCode = "409", description = "cannot delete person")})
    public void deletePerson(@Parameter(description = "person id", required = true) @PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

}