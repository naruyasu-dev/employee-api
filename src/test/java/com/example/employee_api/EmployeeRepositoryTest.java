package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveAndFindAll() {
        Employee employee = new Employee();
        employee.setName("Tanaka");
        employee.setDepartment("Sales");

        employeeRepository.save(employee);

        List<Employee> list = employeeRepository.findAll();

        assertEquals(1, list.size());
        assertEquals("Tanaka", list.get(0).getName());
        assertEquals("Sales", list.get(0).getDepartment());
    }
}