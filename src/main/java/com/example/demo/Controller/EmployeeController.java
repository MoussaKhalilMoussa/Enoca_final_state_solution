package com.example.demo.Controller;


import com.example.demo.Model.Dto.EmployeeDto;
import com.example.demo.Model.Employee;
import com.example.demo.Service.EmployeeService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EmployeeController {

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee (@RequestBody final EmployeeDto employeeDto){
        Employee employee = employeeService.addEmployee(Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }




    @GetMapping("all")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeDto> employeeDto = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }



    @GetMapping (value = "{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final Long id){
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(EmployeeDto.from(employee),HttpStatus.OK);

    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable final Long id){
        Employee employee = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(EmployeeDto.from(employee),HttpStatus.OK);
    }




    @PutMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@PathVariable final Long id ,
                                                    @RequestBody final EmployeeDto employeeDto){
        Employee editedEmployee = employeeService.editEmployee(id, Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(editedEmployee),HttpStatus.OK);
    }





}

























