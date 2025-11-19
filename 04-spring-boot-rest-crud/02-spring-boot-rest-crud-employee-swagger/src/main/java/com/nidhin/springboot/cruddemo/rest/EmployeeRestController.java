package com.nidhin.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nidhin.springboot.cruddemo.entity.Employee;
import com.nidhin.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
       return employeeService.findAll();
    }

    //get employee based on id passed
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if(null == employee){
            throw new RuntimeException("Employee not found: "+employeeId);
        }
        return employee;
    }

    //Add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        //just in case they pass an id in JSON, set it to 0
        // this is to force a save of new item; instead of update.
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    //Add mapping for PUT/employees - update existing employees
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    //add mapping for PATCH /employees/{employeeId} - Partial update

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){
        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee id not found");
        }

        //throw exception if request body contains id
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body"+ employeeId);
        }

        Employee patchedEmployee = apply(patchPayload, employee);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {
        //convert employee object to JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);

        //convert patchPayload map to JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //Merge the patch updates into object nodes
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    //add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee not found! "+employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - "+employeeId;
    }

}
