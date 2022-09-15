package com.example.demo.Service;


import com.example.demo.Model.Company;
import com.example.demo.Model.Employee;
import com.example.demo.Model.exceptions.CompanyNotFoundException;
import com.example.demo.Model.exceptions.EmployeeIsAlreadyAssignedException;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeService employeeService;


    @Autowired
    public CompanyService(CompanyRepository companyRepository, EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.employeeService = employeeService ;
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getCompanies(){
        return StreamSupport.stream(companyRepository.findAll().spliterator() , false)
                .collect(Collectors.toList());
    }

    public Company getCompany(Long id){
        return companyRepository.findById(id).orElseThrow(()->
                new CompanyNotFoundException(id));

    }


    public Company deleteCompany(Long id){
        Company company = getCompany(id);
        companyRepository.delete(company);
        return company;
    }


    @Transactional
    public Company editCompany(Long id , Company company){
        Company companyToEdit = getCompany(id);
        companyToEdit.setCompanyName(company.getCompanyName());
        return companyToEdit;
    }


    @Transactional
    public Company addEmployeeToCompany(Long companyId , Long employeeId){
        Company company = getCompany(companyId);
        Employee employee= employeeService.getEmployee(employeeId);
        if(Objects.nonNull(employee.getCompany())){
            throw new EmployeeIsAlreadyAssignedException(employeeId,employee.getCompany().getId());
        }
        company.addEmployee(employee);
        return  company;
    }


    @Transactional
    public Company removeEmployeeFromCompany(Long companyId ,Long employeeId ){
        Company company = getCompany(companyId);
        Employee employee = employeeService.getEmployee(employeeId);
        company.removeEmployee(employee);
        return company;
    }


}
