package br.com.restfull.service;

import br.com.restfull.converter.DozerConverter;
import br.com.restfull.converter.custom.PersonConverter;
import br.com.restfull.data.dto.PersonDTO;
import br.com.restfull.data.model.Person;
import br.com.restfull.exception.ResourceNotFoundException;
import br.com.restfull.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter translator;

    public PersonDTO create(PersonDTO person) {
        Person entity = DozerConverter.traslatorObject(person, Person.class);
        PersonDTO vo = DozerConverter.traslatorObject(repository.save(entity), PersonDTO.class);
         return vo;
    }

    public PersonDTO update(PersonDTO person) {
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDTO vo = DozerConverter.traslatorObject(repository.save(entity), PersonDTO.class);
        return vo;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);
    }

    public PersonDTO findById(Long id) {
        Person entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found this ID"));
        return DozerConverter.traslatorObject(entity, PersonDTO.class);
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {
        repository.disablePerson(id);
        Person entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found this ID"));
        return DozerConverter.traslatorObject(entity, PersonDTO.class);
    }

    public Page<PersonDTO> findAll(Pageable pageable) {
        Page<Person> page = repository.findAll(pageable);
        return page.map(this::convertToPersonDTO);
    }

    private PersonDTO convertToPersonDTO(Person entity) {
        return DozerConverter.traslatorObject(entity, PersonDTO.class);
    }
}

