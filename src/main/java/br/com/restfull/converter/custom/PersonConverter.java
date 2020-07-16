package br.com.restfull.converter.custom;


import br.com.restfull.data.model.Person;
import br.com.restfull.data.dto.PersonDTO;
import org.springframework.stereotype.Service;

@Service
public class PersonConverter {

    public PersonDTO convertEntityToDTO(Person person) {
        PersonDTO vo = new PersonDTO();
        vo.setKey(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertDTOToEntity(PersonDTO person) {
        Person entity = new Person();
        entity.setId(person.getKey());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }
}
