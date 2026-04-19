package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void findAll_社員一覧取得() {
        List<Employee> employees = List.of(
                new Employee(1L, "Tanaka", "Sales"),
                new Employee(2L, "Suzuki", "IT")
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findAll();

        assertEquals(2, result.size());
        assertEquals("Tanaka", result.get(0).getName());
    }

    @Test
    void findById_1件取得() {
        Employee employee = new Employee(1L, "Tanaka", "Sales");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(1L);

        assertEquals("Tanaka", result.getName());
    }

    @Test
    void add_社員追加() {
        Employee employee = new Employee(3L, "Sato", "HR");

        employeeService.add(employee);

        verify(employeeRepository).save(employee);
    }

    @Test
    void update_社員更新() {
        Employee employee = new Employee(1L, "Tanaka", "Finance");

        employeeService.update(employee);

        verify(employeeRepository).save(employee);
    }

    @Test
    void delete_社員削除() {
        employeeService.delete(1L);

        verify(employeeRepository).deleteById(1L);
    }
}