package spring.boot.tdd.springtdd.service;

import org.springframework.stereotype.Service;
import spring.boot.tdd.springtdd.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sharifahmed
 * @since 2019-09-09
 */
@Service
public class PersonService {

    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person(1, "Mohammad", "Ibrahim"));
        persons.add(new Person(3, "Musa", "Ahmed"));
        persons.add(new Person(5, "Sharif", "Ahmed"));
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person addPerson(Person person) {
        persons.add(person);
        return person;
    }
}
