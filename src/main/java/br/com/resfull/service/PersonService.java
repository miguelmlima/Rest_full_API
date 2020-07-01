package br.com.resfull.service;

import br.com.resfull.ExceptionResponse.ResourceNotFoundException;
import br.com.resfull.converter.DozerConverter;
import br.com.resfull.data.model.Person;
import br.com.resfull.data.vo.PersonVO;
import br.com.resfull.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public PersonVO create(PersonVO person) {
        Person entity = DozerConverter.traslatorObject(person, Person.class);
        PersonVO vo = DozerConverter.traslatorObject(repository.save(entity), PersonVO.class);
         return vo;
    }

    public PersonVO update(PersonVO person) {
        Person entity = repository.findById(person.getId())
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

