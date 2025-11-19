package com.nidhin.springboot.cruddemo.dao;

import com.nidhin.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //No need to write implementation class, as basic CRUD operations are provided by JpaRepository
}
