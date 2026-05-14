package com.example.employee_api.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.employee_api.model.Employee;

class EmployeeTest {

    @Test
    void デフォルトコンストラクタで生成できる() {
        Employee emp = new Employee();

        assertNull(emp.getId());
        assertNull(emp.getName());
        assertNull(emp.getDepartmentId());
        assertNull(emp.getDepartmentName());
    }

    @Test
    void 全項目コンストラクタで値を設定できる() {
        Employee emp = new Employee(1L, "山田太郎", 2L, "開発部");

        assertEquals(1L, emp.getId());
        assertEquals("山田太郎", emp.getName());
        assertEquals(2L, emp.getDepartmentId());
        assertEquals("開発部", emp.getDepartmentName());
    }

    @Test
    void setterとgetterで値を設定取得できる() {
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