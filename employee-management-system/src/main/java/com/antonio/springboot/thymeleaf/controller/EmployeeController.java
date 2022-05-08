package com.antonio.springboot.thymeleaf.controller;

import com.antonio.springboot.thymeleaf.service.EmployeeService;
import com.antonio.springboot.thymeleaf.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Since we have only one constructor @Autowired is optional
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get employees from database
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        //Our thymeleaf template will access this data for binding form data
        theModel.addAttribute("employee", theEmployee);

        return "employees/employe-form";
    }

    @PostMapping("/save")
    //th:object="${employee}" is going to passed the form data to @ModelAttribute("employee")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions, Post/Redirect/Get pattern
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    //@RequestParam it will catch the employeeId that is in the URL and it is going to insert into theId
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);
        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);
        // send over to our form
        return "employees/employe-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        //delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";
    }
}









