package com.example.employee_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.employee_api.mapper.EmployeeMapper;
import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;

class EmployeeServiceTest {

    private EmployeeMapper employeeMapper;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeMapper = Mockito.mock(EmployeeMapper.class);
        employeeService = new EmployeeService(employeeMapper);
    }

    @Test
    void findAllで社員一覧を取得できる() {
        List<Employee> employees = List.of(
                new Employee(1L, "山田太郎", 2L, "開発部"),
                new Employee(2L, "佐藤花子", 3L, "総務部")
        );

        when(employeeMapper.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findAll();

        assertEquals(2, result.size());
        assertEquals("山田太郎", result.get(0).getName());
        assertEquals(2L, result.get(0).getDepartmentId());
        assertEquals("開発部", result.get(0).getDepartmentName());
    }

    @Test
    void findByIdで社員を1件取得できる() {
        Employee employee = new Employee(1L, "山田太郎", 2L, "開発部");

        when(employeeMapper.findById(1L)).thenReturn(employee);

        Employee result = employeeService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("山田太郎", result.getName());
        assertEquals(2L, result.getDepartmentId());
        assertEquals("開発部", result.getDepartmentName());
    }

    @Test
    void addで社員を登録できる() {
        Employee employee = new Employee(null, "田中一郎", 2L, "開発部");

        employeeService.add(employee);

        verify(employeeMapper).insert(employee);
    }

    @Test
    void updateで社員を更新できる() {
        Employee employee = new Employee(1L, "山田太郎", 3L, "総務部");

        employeeService.update(employee);

        verify(employeeMapper).update(employee);
    }

    @Test
    void deleteで社員を削除できる() {
        employeeService.delete(1L);

        verify(employeeMapper).delete(1L);
    }
}