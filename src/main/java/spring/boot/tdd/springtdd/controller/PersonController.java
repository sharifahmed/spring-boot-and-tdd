package spring.boot.tdd.springtdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.tdd.springtdd.domain.Person;
import spring.boot.tdd.springtdd.service.PersonService;

import java.util.List;

/**
 * @author sharifahmed
 * @since 2019-09-09
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @PostMapping(value = "/addPerson")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(new Person(person.getId(), person.getFirstName(), person.getLastName()));
    }
}
