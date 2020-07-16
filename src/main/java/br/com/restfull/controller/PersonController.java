package br.com.restfull.controller;


import br.com.restfull.data.dto.PersonDTO;
import br.com.restfull.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Person Crud Endpoints")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}",produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "fetch person data by your ID")
    public PersonDTO findById(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.findById(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "returns everyone's data")
    public ResponseEntity<PagedModel<PersonDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler assembler) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"firstName"));

        Page<PersonDTO> persons = service.findAll(pageable);
        persons
             .stream()
             .forEach(p -> p.add(
                      linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                   )
                );
        return new ResponseEntity<>(assembler.toModel(persons), HttpStatus.OK);
    }
    @GetMapping(value = "/findPersonByName/{firstName}",produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "returns everyone's data")
    public ResponseEntity<PagedModel<PersonDTO>> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler assembler) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"firstName"));

        Page<PersonDTO> persons = service.findPersonByName(firstName, pageable);
        persons
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                        )
                );
        return new ResponseEntity<>(assembler.toModel(persons), HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "register a person")
    public PersonDTO create(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.create(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }
    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "updates a person field by your ID")
    public PersonDTO update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.update(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "erases person's data by your ID")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping(value = "/{id}",produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Disable a specific person by your ID")
    public PersonDTO disablePerson(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.disablePerson(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }
}
