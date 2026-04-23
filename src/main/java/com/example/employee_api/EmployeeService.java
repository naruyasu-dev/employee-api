package com.example.employee_api;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    public Employee findById(Long id) {
        return employeeMapper.findById(id);
    }

    public void add(Employee employee) {
        employeeMapper.insert(employee);
    }

    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    public void delete(Long id) {
        employeeMapper.delete(id);
    }

    public List<Employee> search(String keyword, String department) {
        return employeeMapper.search(keyword, department);
    }
}