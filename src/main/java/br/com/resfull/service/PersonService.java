package br.com.resfull.service;

import br.com.resfull.ExceptionResponse.ResourceNotFoundException;
import br.com.resfull.model.Person;
import br.com.resfull.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        Person entity = repository.findById(person.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(entity);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);
    }

    public Person findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found this ID"));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }
}

