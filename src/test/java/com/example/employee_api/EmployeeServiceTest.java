package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void findAll_社員一覧取得() {
        List<Employee> employees = List.of(
            new Employee(1L, "Tanaka", "Sales"),
            new Employee(2L, "Suzuki", "IT")
        );

        when(employeeMapper.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findAll();

        assertEquals(2, result.size());
    }
}