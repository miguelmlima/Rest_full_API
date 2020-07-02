package br.com.resfull.converter.mocks;

import br.com.resfull.data.model.Person;
import br.com.resfull.data.vo.v1.PersonVO;

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
        List<Person> people = new ArrayList<>();
        for (int i =0; i <14; i++) {
            people.add(mockEntity(i));
        }
        return people;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> personVOS = new ArrayList<>();
        for (int i=0; i <14; i++) {
            personVOS.add(mockVO(i));
        }
        return personVOS;
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

    private PersonVO mockVO(Integer number) {
        PersonVO personVO = new PersonVO();
        personVO.setFirstName("First name" + number);
        personVO.setLastName("Last name" + number);
        personVO.setGender(((number % 2) == 0) ? "Male" : "Female");
        personVO.setAddress("Address" + number);
        personVO.setId(number.longValue());
        return personVO;
    }
}
