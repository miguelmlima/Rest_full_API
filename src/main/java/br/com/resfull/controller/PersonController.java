package br.com.resfull.controller;


import br.com.resfull.data.vo.v1.PersonVO;
import br.com.resfull.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}",produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO personVO = service.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonVO> findAll() {
        List<PersonVO> persons = service.findAll();
        persons
             .stream()
             .forEach(p -> p.add(
                      linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                   )
                );
        return persons;
    }
    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = service.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }
    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = service.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
