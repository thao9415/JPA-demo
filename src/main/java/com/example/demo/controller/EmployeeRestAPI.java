package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestAPI {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<?> getListEmployee() {
        List<Employee> employeeList = (List<Employee>) employeeService.findAll();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setUpdate(false);
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployeeById(@Valid @RequestBody Employee employee, @PathVariable int id) {

        Optional<Employee> employee1 = employeeService.findById(id);
        if (!employee1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employee1.get().setName(employee.getName());
        employee1.get().setSalary(employee.getSalary());
        employee1.get().setUpdate(true);
        employeeService.save(employee1.get());

        return new ResponseEntity<>(employee1, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employee/search-by-name")
    public ResponseEntity<?> findByName(@RequestBody Employee employee) {
       String name = employee.getName();
        if (name == "" || name == null) {
            List<Employee> employeeList = (List<Employee>) employeeService.findAll();
            if (employeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        }
        List<Employee> employeeList = (List<Employee>) employeeService.findByName(name);
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
}
