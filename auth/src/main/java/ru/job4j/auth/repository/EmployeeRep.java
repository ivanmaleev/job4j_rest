package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.auth.domain.Employee;

import java.util.Collection;

@Repository
public interface EmployeeRep extends CrudRepository<Employee, Integer> {
    @Override
    Collection<Employee> findAll();
}
