package ru.job4j.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRep;

import java.util.Collection;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRep personRep;

    @Autowired
    public PersonService(PersonRep personRep) {
        this.personRep = personRep;
    }

    public Collection<Person> findAll() {
        return personRep.findAll();
    }

    public Optional<Person> findById(int id) {
        return personRep.findById(id);
    }

    public Person save(Person person) {
        return personRep.save(person);
    }

    public void delete(Person person) {
        personRep.delete(person);
    }
}
