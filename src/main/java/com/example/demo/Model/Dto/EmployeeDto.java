package com.example.demo.Model.Dto;

import com.example.demo.Model.Company;
import com.example.demo.Model.Employee;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Objects;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EmployeeDto {

    private Long id;
    private String employeeName;
    private Long age;
    private Long salary;
    private Long working_Year;

    public static EmployeeDto from(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
