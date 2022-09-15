package com.example.demo.Service;


import com.example.demo.Model.Dto.EmployeeDto;
import com.example.demo.Model.Employee;
import com.example.demo.Model.exceptions.EmployeeNotFoundException;
import com.example.demo.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EmployeeService {


    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }


    public List<Employee> getEmployees(){
        return StreamSupport.stream(employeeRepository.findAll().spliterator() , false)
                .collect(Collectors.toList());
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException(id));
    }

    public Employee deleteEmployee(Long id){
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Transactional
    public Employee editEmployee(Long id , Employee employee) {
        Employee employeeToEdit = getEmployee(id);
        employeeToEdit.setEmployeeName(employee.getEmployeeName());
        employeeToEdit.setAge(employee.getAge());
        employeeToEdit.setSalary(employee.getSalary());
        employeeToEdit.setWorking_Year(employee.getWorking_Year());
        return employeeToEdit;

    }





}














