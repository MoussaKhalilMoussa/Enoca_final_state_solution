package com.example.demo.Model;

import com.example.demo.Model.Dto.CompanyDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "Company")



public class Company {
    @Id
    @SequenceGenerator(name = "company_sequence",
    sequenceName = "company_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "company_sequence")
    private Long id;
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL ,targetEntity = Employee.class )
    @JoinColumn(name = "company_Id" , referencedColumnName = "id")
    private List<Employee> employees = new ArrayList<>();


    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }


    public static Company from(CompanyDto companyDto){
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setCompanyName(companyDto.getCompanyName());
        return company;
    }






}
