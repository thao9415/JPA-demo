package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    Iterable<Employee> findEmployeesByNameContaining(String employee_name);
}