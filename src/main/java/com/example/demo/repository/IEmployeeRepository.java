package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    Iterable<Employee> findEmployeesByNameContaining(String employee_name);

    @Query(value = "select e from Employee e " + "where e.name = :name")
    Iterable<Employee> findEmployeesUseQuery(@Param("name") String name);
}