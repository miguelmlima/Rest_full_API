package br.com.restfull.service;

import br.com.restfull.ExceptionResponse.ResourceNotFoundException;
import br.com.restfull.converter.DozerConverter;
import br.com.restfull.converter.custom.PersonConverter;
import br.com.restfull.data.model.Person;
import br.com.restfull.data.vo.v1.PersonVO;
import br.com.restfull.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter translator;

    public PersonVO create(PersonVO person) {
        Person entity = DozerConverter.traslatorObject(person, Person.class);
        PersonVO vo = DozerConverter.traslatorObject(repository.save(entity), PersonVO.class);
         return vo;
    }

    public PersonVO update(PersonVO person) {
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerConverter.traslatorObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);
    }

    public PersonVO findById(Long id) {
        Person entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found this ID"));
        return DozerConverter.traslatorObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerConverter.translatorObjectList(repository.findAll(), PersonVO.class);
    }
}

