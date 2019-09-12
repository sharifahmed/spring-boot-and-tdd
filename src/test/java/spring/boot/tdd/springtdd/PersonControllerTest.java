package spring.boot.tdd.springtdd;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.boot.tdd.springtdd.controller.PersonController;
import spring.boot.tdd.springtdd.domain.Person;
import spring.boot.tdd.springtdd.service.PersonService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(value = SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private List<Person> mockPersons = Arrays.asList(
            new Person(1, "Mohammad", "Ibrahim"),
            new Person(3, "Musa", "Ahmed"),
            new Person(5, "Sharif", "Ahmed"));

    @Test
    public void getPersons() throws Exception {
        Mockito.when(personService.getPersons()).thenReturn(mockPersons);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(getExpectedPersons(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void addPerson() throws Exception {
        Person mockPerson = new Person(7, "Mohammad", "Ali");

        Mockito.when(personService.addPerson(Mockito.any(Person.class))).thenReturn(mockPerson);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addPerson")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:7,firstName:Mohammad,lastName:Ali}";

        assertEquals(expected, result.getResponse().getContentAsString());
    }

    private String getExpectedPersons() {
        return "[{id:1,firstName:Mohammad,lastName:Ibrahim}," +
                "{id:3,firstName:Musa,lastName:Ahmed}," +
                "{id:5,firstName:Sharif,lastName:Ahmed}]";
    }
}
