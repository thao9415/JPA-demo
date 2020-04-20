package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findById(int id);

    Iterable<Employee> findAll();

    Employee save(Employee employee);

    void delete(int id);

    Iterable<Employee> findByName(String name);
}
