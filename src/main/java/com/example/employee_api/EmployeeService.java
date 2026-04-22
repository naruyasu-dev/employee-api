package com.example.employee_api;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void add(Employee employee) {
        employeeRepository.save(employee);
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
    public List<Employee> searchByNameAndDepartment(String keyword, String department) {
        return employeeRepository
                .findByNameContainingAndDepartmentContaining(keyword, department);
    }
}