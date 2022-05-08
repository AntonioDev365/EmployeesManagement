package com.antonio.springboot.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antonio.springboot.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //add a method to sort by id
    //Spring Data JPA will parse the method name
    //Looks for a specific format and pattern, Creates appropiate query, behind the scenes
    public List<Employee> findAllByOrderByIdAsc();
}
