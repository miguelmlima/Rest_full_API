package br.com.restfull.converter;

import br.com.restfull.converter.mocks.MockPerson;
import br.com.restfull.data.model.Person;
import br.com.restfull.data.dto.PersonDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class DozerConverterTest {

    MockPerson inputObject;

    @Before
    public void setup () {
        inputObject = new MockPerson();
    }

    @Test
    public void translatorEntityToVOTest() {
     PersonDTO output = DozerConverter.traslatorObject(inputObject.mockEntity(), PersonDTO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("First name0", output.getFirstName());
        Assert.assertEquals("Last name0", output.getLastName());
        Assert.assertEquals("Address0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void translatorEntityListToVOListTest() {
        List<PersonDTO> outputList = DozerConverter.translatorObjectList(inputObject.mockEntityList(), PersonDTO.class);
        PersonDTO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("First name0", outputZero.getFirstName());
        Assert.assertEquals("Last name0", outputZero.getLastName());
        Assert.assertEquals("Address0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());

        PersonDTO outputSix = outputList.get(6);

        Assert.assertEquals(Long.valueOf(6L), outputSix.getKey());
        Assert.assertEquals("First name6", outputSix.getFirstName());
        Assert.assertEquals("Last name6", outputSix.getLastName());
        Assert.assertEquals("Address6", outputSix.getAddress());
        Assert.assertEquals("Male", outputSix.getGender());

        PersonDTO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("First name7", outputSeven.getFirstName());
        Assert.assertEquals("Last name7", outputSeven.getLastName());
        Assert.assertEquals("Address7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());
    }

    @Test
    public void translatorVOToEntityTest() {
        Person output = DozerConverter.traslatorObject(inputObject.mockVO(), Person.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First name0", output.getFirstName());
        Assert.assertEquals("Last name0", output.getLastName());
        Assert.assertEquals("Address0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void translatorVOListToEntityTest() {
        List<Person> outputList = DozerConverter.translatorObjectList(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(2);

        Assert.assertEquals(Long.valueOf(2L), outputZero.getId());
        Assert.assertEquals("First name2", outputZero.getFirstName());
        Assert.assertEquals("Last name2", outputZero.getLastName());
        Assert.assertEquals("Address2", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());

        Person outputTen = outputList.get(10);

        Assert.assertEquals(Long.valueOf(10L), outputTen.getId());
        Assert.assertEquals("First name10", outputTen.getFirstName());
        Assert.assertEquals("Last name10", outputTen.getLastName());
        Assert.assertEquals("Address10", outputTen.getAddress());
        Assert.assertEquals("Male", outputTen.getGender());

        Person outputEleven = outputList.get(11);

        Assert.assertEquals(Long.valueOf(11L), outputEleven.getId());
        Assert.assertEquals("First name11", outputEleven.getFirstName());
        Assert.assertEquals("Last name11", outputEleven.getLastName());
        Assert.assertEquals("Address11", outputEleven.getAddress());
        Assert.assertEquals("Female", outputEleven.getGender());
    }
}
