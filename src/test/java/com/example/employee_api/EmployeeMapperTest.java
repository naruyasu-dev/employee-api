package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.employee_api.model.Employee;

class EmployeeMapperTest {

    @Test
    void Employeeに部署IDと部署名を設定できる() {
        Employee emp = new Employee();

        emp.setId(1L);
        emp.setName("山田太郎");
        emp.setDepartmentId(2L);
        emp.setDepartmentName("開発部");

        assertEquals(1L, emp.getId());
        assertEquals("山田太郎", emp.getName());
        assertEquals(2L, emp.getDepartmentId());
        assertEquals("開発部", emp.getDepartmentName());
    }
}