package br.com.resfull.controller;


import br.com.resfull.data.vo.v1.PersonVO;
import br.com.resfull.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    @GetMapping
    public List<PersonVO> findAll() {
        return service.findAll();
    }
    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }
    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
