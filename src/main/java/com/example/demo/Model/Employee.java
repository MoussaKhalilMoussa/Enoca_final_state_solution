package com.example.demo.Model;


import com.example.demo.Model.Dto.EmployeeDto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeName;
    private Long age;
    private Long salary;
    private Long working_Year;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    public Employee() {
    }

     public static Employee from(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employee.setWorking_Year(employeeDto.getWorking_Year());
        return employee;
     }

     @Transactional
    public Long calculateSalary (){
       Long sal = (salary + (salary*10)/100 * working_Year);
        if(age>=20 || age <= 25){
            sal = sal + (salary * 10)/100;
            return sal;
        } else if (age>=26 || age<=30){
            sal = sal + (salary * 8)/100;
            return sal;
        } else if (age>=31 || age<=36) {
            sal = sal + (salary * 5)/100;
            return sal;
        } else if (age>36){
            sal = sal + (salary * 3)/100;
            return sal;
        }
        return salary;
    }
    public Long getSalary() {
        return salary = calculateSalary();
    }
}
