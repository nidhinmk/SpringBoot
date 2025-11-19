package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //add mapping for list
    @GetMapping("/list")
    public String listEmployees(Model model){
        //get the employees from db
        List<Employee> employeeList = employeeService.findAll();

        //add it to the model
        model.addAttribute("employees", employeeList);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model){
        //get teh employee
        Employee theEmployee = employeeService.findById(employeeId);

        //set the employee in the model to prepopulate the form
        model.addAttribute("employee", theEmployee);

        //send over to the form

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        //save the employee
        employeeService.save(employee);

        //use redirect to prevent duplicate subsmission
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId){
        //save the employee
        employeeService.deleteById(employeeId);

        //use redirect to prevent duplicate subsmission
        return "redirect:/employees/list";
    }

}
