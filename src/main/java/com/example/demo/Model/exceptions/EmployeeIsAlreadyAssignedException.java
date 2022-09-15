package com.example.demo.Model.exceptions;

import java.text.MessageFormat;

public class EmployeeIsAlreadyAssignedException extends RuntimeException{

    public EmployeeIsAlreadyAssignedException(final Long employeeId , final Long companyId){
       super(MessageFormat.format("employee : {0} is already assigned to company : {1}",employeeId,companyId));
    }
}
