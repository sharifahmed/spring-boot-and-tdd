package spring.boot.tdd.springtdd;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.tdd.springtdd.domain.Person;

import java.util.List;

/**
 * @author sharifahmed
 * @since 2019-09-12
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SpringTddApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalControllerIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void addPerson() throws JSONException {
        Person person = new Person(7, "Mohammad", "Ali");

        HttpEntity<Person> httpEntity = new HttpEntity<>(person, httpHeaders);
        String url = "http://localhost:" + port + "/addPerson";

        ResponseEntity<Person> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Person.class);

        JSONAssert.assertEquals(person.toString(), String.valueOf(responseEntity.getBody()), false);
    }

    @Test
    public void getPersons() throws JSONException {
        HttpEntity<List<Person>> httpEntity = new HttpEntity<>(null, httpHeaders);
        String url = "http://localhost:" + port + "/persons";

        ResponseEntity<List> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);

        String expected = "[{id=1, firstName=Mohammad, lastName=Ibrahim}," +
                " {id=3, firstName=Musa, lastName=Ahmed}," +
                " {id=5, firstName=Sharif, lastName=Ahmed}," +
                " {id=7, firstName=Mohammad, lastName=Ali}]";

        JSONAssert.assertEquals(expected, responseEntity.getBody().toString(), false);
    }
}
