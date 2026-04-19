package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void testDefaultConstructorAndSetterGetter() {
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setName("Tanaka");
        employee.setDepartment("Sales");

        assertEquals(1L, employee.getId());
        assertEquals("Tanaka", employee.getName());
        assertEquals("Sales", employee.getDepartment());
    }

    @Test
    void testAllArgsConstructor() {
        Employee employee = new Employee(1L, "Tanaka", "Sales");

        assertEquals(1L, employee.getId());
        assertEquals("Tanaka", employee.getName());
        assertEquals("Sales", employee.getDepartment());
    }
}