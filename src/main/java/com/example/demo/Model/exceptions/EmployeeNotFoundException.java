package com.example.demo.Model.exceptions;

import java.text.MessageFormat;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id){
        super(MessageFormat.format("Could not found employee with id : {0}",id));
    }
}
