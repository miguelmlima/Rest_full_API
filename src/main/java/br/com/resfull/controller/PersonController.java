package br.com.resfull.controller;


import br.com.resfull.data.vo.PersonVO;
import br.com.resfull.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/PersonVO")
public class PersonVOController {

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
    public PersonVO create(@RequestBody PersonVO PersonVO) {
        return service.create(PersonVO);
    }
    @PutMapping
    public PersonVO update(@RequestBody PersonVO PersonVO) {
        return service.update(PersonVO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
         service.delete(id);
         return ResponseEntity.ok().build();
    }
}
