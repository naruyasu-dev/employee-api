package com.example.employee_api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeViewController.class)
class EmployeeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    // ===== GET一覧 =====
    @Test
    void testShowEmployees() throws Exception {
        List<Employee> list = Arrays.asList(
                new Employee(1L, "Tanaka", "Sales"),
                new Employee(2L, "Sato", "HR")
        );

        when(employeeService.findAll()).thenReturn(list);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("employees"));
    }

    // ===== POST追加（成功） =====
    @Test
    void testAddEmployee() throws Exception {
        mockMvc.perform(post("/employees-ui/add")
                .param("name", "Tanaka")
                .param("department", "Sales"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService).add(any(Employee.class));
    }

    // ===== POST追加（バリデーションエラー） =====
    @Test
    void testAddEmployee_validationError() throws Exception {
        when(employeeService.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(post("/employees-ui/add")
                .param("name", "") // ← エラー
                .param("department", "Sales"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().hasErrors());

        verify(employeeService, never()).add(any(Employee.class));
    }

    // ===== POST更新 =====
    @Test
    void testUpdateEmployee() throws Exception {
        mockMvc.perform(post("/employees-ui/update")
                .param("id", "1")
                .param("name", "Tanaka")
                .param("department", "Sales"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService).update(any(Employee.class));
    }

    // ===== POST削除 =====
    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(post("/employees-ui/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService).delete(1L);
    }
}