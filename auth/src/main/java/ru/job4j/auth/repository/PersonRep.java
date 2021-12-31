package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.auth.domain.Person;

import java.util.Collection;

@Repository
public interface PersonRep extends CrudRepository<Person, Integer> {
    @Override
    Collection<Person> findAll();
}
