package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public interface EmployeeRepository extends CrudRepository<Employee,Long> {


}
