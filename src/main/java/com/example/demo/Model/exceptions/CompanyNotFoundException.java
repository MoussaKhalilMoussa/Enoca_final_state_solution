package com.example.demo.Model.exceptions;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

import java.text.MessageFormat;

public class CompanyNotFoundException extends RuntimeJsonMappingException {


    public CompanyNotFoundException (Long id){
        super(MessageFormat.format("could not find company with id {0} :" ,id));
    }
}
