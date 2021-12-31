package ru.job4j.auth.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.PersonService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindAll() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("root");
        person.setPassword("root");
        Collection<Person> persons = List.of(person);
        Mockito.when(personService.findAll()).thenReturn(persons);
        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/person/");
        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
        String expected = "[{\"id\":1,\"login\":\"root\",\"password\":\"root\"}]";
        assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void whenFindByIdOk() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("root");
        person.setPassword("root");
        Optional<Person> personOptional = Optional.of(person);
        Mockito.when(personService.findById(1)).thenReturn(personOptional);
        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/person/1");
        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
        String expected = "{\"id\":1,\"login\":\"root\",\"password\":\"root\"}";
        assertEquals(expected, mvcResult.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenFindByIdNotFound() throws Exception {
        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/person/2");
        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenCreate() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("root");
        person.setPassword("root");
        Mockito.when(personService.save(person)).thenReturn(person);
        String expected = "{\"id\":1,\"login\":\"root\",\"password\":\"root\"}";
        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/person/")
                .accept(MediaType.APPLICATION_JSON)
                .content(expected)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenUpdate() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("root");
        person.setPassword("root");
        Mockito.when(personService.save(person)).thenReturn(person);
        String expected = "{\"id\":1,\"login\":\"root\",\"password\":\"root\"}";
        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.put("/person/")
                .accept(MediaType.APPLICATION_JSON)
                .content(expected)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenDelete() throws Exception {
        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/person/1");
        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

}