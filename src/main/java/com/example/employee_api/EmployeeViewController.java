package com.example.employee_api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class EmployeeViewController {

    private final EmployeeService employeeService;
    public EmployeeViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees-ui")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("employee", new Employee());
        return "employees";
    }

    @PostMapping("/employees-ui/add")
    public String addEmployee(@Valid @ModelAttribute Employee employee,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "employees";
        }

        employeeService.add(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employees-ui/delete")
    public String deleteEmployee(@RequestParam Long id) {
    	employeeService.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    @PostMapping("/employees-ui/update")
    public String updateEmployee(@Valid @ModelAttribute Employee employee,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "edit-employee";
        }

        employeeService.update(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam String keyword,
                                  @RequestParam(required = false, defaultValue = "") String department,
                                  Model model) {

        model.addAttribute("employees",
        		employeeService.searchByNameAndDepartment(
                        keyword, department));

        model.addAttribute("employee", new Employee());
        return "employees";
    }
}