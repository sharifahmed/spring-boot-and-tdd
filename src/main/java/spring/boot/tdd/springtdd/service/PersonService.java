package spring.boot.tdd.springtdd.service;

import org.springframework.stereotype.Service;
import spring.boot.tdd.springtdd.domain.Person;

import java.util.Arrays;
import java.util.List;

/**
 * @author sharifahmed
 * @since 2019-09-09
 */
@Service
public class PersonService {

    private static final List<Person> persons = Arrays.asList(
            new Person(1, "Mohammad", "Ibrahim"),
            new Person(3, "Musa", "Ahmed"),
            new Person(5, "Sharif", "Ahmed"));

    public List<Person> getPersons() {
        return persons;
    }
}
