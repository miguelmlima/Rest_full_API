package br.com.resfull.converter;

import br.com.resfull.converter.mocks.MockPerson;
import br.com.resfull.data.model.Person;
import br.com.resfull.data.vo.PersonVO;
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
        PersonVO output = DozerConverter.traslatorObject(inputObject.mockEntity(), PersonVO.class);
        Assert.assertEquals(Long.valueOf(1L), output.getId());
        Assert.assertEquals("Fist Name test1", output.getFirstName());
        Assert.assertEquals("Last Name test1", output.getLastName());
        Assert.assertEquals("Address", output.getAddress());
        Assert.assertEquals("Female", output.getGender());
    }

    @Test
    public void translatorEntityListToVOListTest() {
        List<PersonVO> outputList = DozerConverter.translatorObjectList(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name test2", outputZero.getFirstName());
        Assert.assertEquals("Last Name test2", outputZero.getLastName());
        Assert.assertEquals("Address test2", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());

        PersonVO outputSix = outputList.get(6);

        Assert.assertEquals(Long.valueOf(6L), outputSix.getId());
        Assert.assertEquals("First Name test6", outputSix.getFirstName());
        Assert.assertEquals("Last Name test6", outputSix.getLastName());
        Assert.assertEquals("Address test6", outputSix.getAddress());
        Assert.assertEquals("Male", outputSix.getGender());

        PersonVO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSix.getId());
        Assert.assertEquals("First Name test7", outputSix.getFirstName());
        Assert.assertEquals("Last Name test7", outputSix.getLastName());
        Assert.assertEquals("Address test7", outputSix.getAddress());
        Assert.assertEquals("Male", outputSix.getGender());
    }

    @Test
    public void translatorVOToEntityTest() {
        Person output = DozerConverter.traslatorObject(inputObject.mockVO(), Person.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Fist Name test0", output.getFirstName());
        Assert.assertEquals("Last Name test0", output.getLastName());
        Assert.assertEquals("Address", output.getAddress());
        Assert.assertEquals("Female", output.getGender());
    }

    @Test
    public void translatorVOListToEntityTest() {
        List<Person> outputList = DozerConverter.translatorObjectList(inputObject.mockEntityList(), Person.class);
        Person outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name test0", outputZero.getFirstName());
        Assert.assertEquals("Last Name test0", outputZero.getLastName());
        Assert.assertEquals("Address test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());

        Person outputTen = outputList.get(10);

        Assert.assertEquals(Long.valueOf(10L), outputTen.getId());
        Assert.assertEquals("First Name test10", outputTen.getFirstName());
        Assert.assertEquals("Last Name test10", outputTen.getLastName());
        Assert.assertEquals("Address test10", outputTen.getAddress());
        Assert.assertEquals("Male", outputTen.getGender());

        Person outputEleven = outputList.get(11);

        Assert.assertEquals(Long.valueOf(7L), outputEleven.getId());
        Assert.assertEquals("First Name test11", outputEleven.getFirstName());
        Assert.assertEquals("Last Name test11", outputEleven.getLastName());
        Assert.assertEquals("Address test11", outputEleven.getAddress());
        Assert.assertEquals("Male", outputEleven.getGender());
    }
}
