package ru.job4j.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.EmployeeService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";
    @Autowired
    private RestTemplate rest;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Collection<Employee> findAll() {
        Collection<Employee> employees = employeeService.findAll();
        List<Person> accounts = rest.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Person>>() {
                }).getBody();
        for (Person account : accounts) {
            for (Employee employee : employees) {
                if (employee.getId() == account.getEmployeeId()) {
                    employee.addAccount(account);
                    break;
                }
            }
        }
        return employees;
    }

    @PostMapping("/")
    public ResponseEntity<Person> addAccount(@RequestBody Person person) {
        Person account = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(
                account,
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateAccount(@RequestBody Person person) {
        rest.put(API, person, Person.class);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
