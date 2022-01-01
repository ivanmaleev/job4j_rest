package ru.job4j.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.repository.EmployeeRep;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRep employeeRep;

    @Autowired
    public EmployeeService(EmployeeRep employeeRep) {
        this.employeeRep = employeeRep;
    }

    public Collection<Employee> findAll() {
        return employeeRep.findAll();
    }

    public Optional<Employee> findById(int id) {
        return employeeRep.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRep.save(employee);
    }

    public void delete(Employee employee) {
        employeeRep.delete(employee);
    }
}
