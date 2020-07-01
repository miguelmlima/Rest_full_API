package br.com.resfull.converter.mocks;

import br.com.resfull.data.model.Person;
import br.com.resfull.data.vo.PersonVO;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return  mockEntity(0);
    }

    public PersonVO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<>();
        for (int i =0; i <14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i=0; i <14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setFirstName("First name test" + number);
        person.setLastName("Last name" + number);
        person.setGender(((number % 2) == 0) ? "male" : "Female");
        person.setAddress("Rua Fradique Coutinho" + number);
        person.setId(number.longValue());
        return person;
    }

    private PersonVO mockVO(Integer number) {
        PersonVO person = new PersonVO();
        person.setFirstName("First name test" + number);
        person.setLastName("Last name" + number);
        person.setGender(((number % 2) == 0) ? "male" : "Female");
        person.setAddress("Rua Fradique Coutinho" + number);
        person.setId(number.longValue());
        return person;
    }
}
