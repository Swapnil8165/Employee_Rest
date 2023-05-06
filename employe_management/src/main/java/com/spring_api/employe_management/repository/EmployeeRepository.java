package com.spring_api.employe_management.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_api.employe_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
