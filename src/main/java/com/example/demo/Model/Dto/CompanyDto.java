package com.example.demo.Model.Dto;

import com.example.demo.Model.Company;
import com.example.demo.Model.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CompanyDto {
    private Long id;
    private String companyName;
    private List<EmployeeDto> employeesDto = new ArrayList<>();

    public static CompanyDto from(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());



        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setEmployeesDto(company.getEmployees().stream().map(EmployeeDto::from).collect(Collectors.toList()));
        return companyDto;

    }

}
