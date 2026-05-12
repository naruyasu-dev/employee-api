package com.example.employee_api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.employee_api.controller.EmployeeRestController;
import com.example.employee_api.mapper.DepartmentMapper;
import com.example.employee_api.mapper.EmployeeMapper;
import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;

@WebMvcTest(EmployeeRestController.class)
class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeMapper employeeMapper;

    @MockBean
    private DepartmentMapper departmentMapper;

    @Test
    void findAllで社員一覧を取得できる() throws Exception {
        when(employeeService.findAll()).thenReturn(
                List.of(
                        new Employee(1L, "山田太郎", 2L, "開発部"),
                        new Employee(2L, "佐藤花子", 3L, "総務部")
                )
        );

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdで社員を1件取得できる() throws Exception {
        when(employeeService.findById(1L))
                .thenReturn(new Employee(1L, "山田太郎", 2L, "開発部"));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void addで社員を登録できる() throws Exception {
        doNothing().when(employeeService).add(any(Employee.class));

        mockMvc.perform(post("/employees")
                .contentType("application/json")
                .content("""
                        {
                          "name": "田中一郎",
                          "departmentId": 2
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void updateで社員を更新できる() throws Exception {
        doNothing().when(employeeService).update(any(Employee.class));

        mockMvc.perform(put("/employees/1")
                .contentType("application/json")
                .content("""
                        {
                          "name": "山田太郎",
                          "departmentId": 3
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void deleteで社員を削除できる() throws Exception {
        doNothing().when(employeeService).delete(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }
}