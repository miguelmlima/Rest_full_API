package br.com.restfull.converter.mocks;

import br.com.restfull.data.model.Person;
import br.com.restfull.data.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return  mockEntity(0);
    }

    public PersonDTO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> people = new ArrayList<>();
        for (int i =0; i <14; i++) {
            people.add(mockEntity(i));
        }
        return people;
    }

    public List<PersonDTO> mockVOList() {
        List<PersonDTO> personDTOS = new ArrayList<>();
        for (int i=0; i <14; i++) {
            personDTOS.add(mockVO(i));
        }
        return personDTOS;
    }

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setFirstName("First name" + number);
        person.setLastName("Last name" + number);
        person.setGender(((number % 2) == 0) ? "Male" : "Female");
        person.setAddress("Address" + number);
        person.setId(number.longValue());
        return person;
    }

    private PersonDTO mockVO(Integer number) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("First name" + number);
        personDTO.setLastName("Last name" + number);
        personDTO.setGender(((number % 2) == 0) ? "Male" : "Female");
        personDTO.setAddress("Address" + number);
        personDTO.setKey(number.longValue());
        return personDTO;
    }
}
