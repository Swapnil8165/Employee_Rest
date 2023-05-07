package com.spring_api.employe_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring_api.employe_management.model.Employee;
import com.spring_api.employe_management.repository.EmployeeRepository;




@RestController
public class controller {

    @Autowired
    private EmployeeRepository employeeRepository;

    
    public ResponseEntity<List<Employee>>getAllEmployee(){
        List<Employee> list = employeeRepository.findAll();
        if(list.isEmpty() || list.size()==0){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Optional<Employee> employee  = employeeRepository.findById(id);
       if(employee.isPresent()){
           return new ResponseEntity<Employee>(employee.get(),HttpStatus.FOUND);
       }
       else{
         return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        try{
            return new ResponseEntity<>(employeeRepository.save(employee),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employee){
        Optional<Employee> emp = employeeRepository.findById(id);

        if(!emp.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Employee employee1 = emp.get();
        employee1.setId(employee.getId());
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee.setPhone(employee.getPhone());
        employeeRepository.save(employee1);

        return ResponseEntity.ok(employee1);
    }

    @DeleteMapping("/employee/{id}")
    public void  deletEmployee(@PathVariable long id){
         employeeRepository.deleteById(id);
         
   
    }   
    
}


  
    

    

   
