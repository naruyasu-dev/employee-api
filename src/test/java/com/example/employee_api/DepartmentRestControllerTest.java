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

import com.example.employee_api.controller.DepartmentRestController;
import com.example.employee_api.mapper.DepartmentMapper;
import com.example.employee_api.mapper.EmployeeMapper;
import com.example.employee_api.model.Department;
import com.example.employee_api.service.DepartmentService;

/**
 * DepartmentRestController のテストクラス
 *
 * @WebMvcTest は Controller 層だけをテストする。
 * Service や Mapper は本物を使わず MockBean にする。
 */
@WebMvcTest(DepartmentRestController.class)
class DepartmentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * DepartmentRestController が使用する Service を Mock 化する。
     */
    @MockBean
    private DepartmentService departmentService;

    /**
     * @WebMvcTest 実行時に MyBatis Mapper が読み込まれて
     * sqlSessionFactory エラーになるのを防ぐため Mock 化する。
     */
    @MockBean
    private DepartmentMapper departmentMapper;

    /**
     * EmployeeMapper も @MapperScan の対象になる場合があるため Mock 化する。
     */
    @MockBean
    private EmployeeMapper employeeMapper;

    /**
     * 部署一覧取得APIのテスト
     *
     * GET /departments
     */
    @Test
    void findAllで部署一覧をJSONで取得できる() throws Exception {

        when(departmentService.findAll()).thenReturn(
                List.of(
                        new Department(1L, "本社", null),
                        new Department(2L, "開発部", 1L),
                        new Department(3L, "総務部", 1L),
                        new Department(4L, "営業部", 1L)
                )
        );

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());
    }

    /**
     * 部署詳細取得APIのテスト
     *
     * GET /departments/1
     */
    @Test
    void findByIdで部署1件を取得できる() throws Exception {

        when(departmentService.findById(1L))
                .thenReturn(new Department(1L, "本社", null));

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk());
    }

    /**
     * 存在しない部署IDを指定した場合のテスト
     *
     * GET /departments/999
     */
    @Test
    void findByIdで存在しない部署の場合は空レスポンスになる() throws Exception {

        when(departmentService.findById(999L)).thenReturn(null);

        mockMvc.perform(get("/departments/999"))
                .andExpect(status().isOk());
    }

    /**
     * 部署登録APIのテスト
     *
     * POST /departments
     */
    @Test
    void addで部署を追加できる() throws Exception {

        doNothing().when(departmentService).add(any(Department.class));

        mockMvc.perform(post("/departments")
                .contentType("application/json")
                .content("""
                        {
                          "name": "人事部",
                          "parentId": 1
                        }
                        """))
                .andExpect(status().isOk());
    }

    /**
     * 部署更新APIのテスト
     *
     * PUT /departments/2
     */
    @Test
    void updateで部署を更新できる() throws Exception {

        doNothing().when(departmentService).update(any(Department.class));

        mockMvc.perform(put("/departments/2")
                .contentType("application/json")
                .content("""
                        {
                          "name": "システム開発部",
                          "parentId": 1
                        }
                        """))
                .andExpect(status().isOk());
    }

    /**
     * 部署削除APIのテスト
     *
     * DELETE /departments/2
     */
    @Test
    void deleteで部署を削除できる() throws Exception {

        doNothing().when(departmentService).delete(2L);

        mockMvc.perform(delete("/departments/2"))
                .andExpect(status().isOk());
    }

    /**
     * 部署検索APIのテスト
     *
     * GET /departments/search?keyword=開発
     */
    @Test
    void searchで部署を検索できる() throws Exception {

        when(departmentService.search("開発")).thenReturn(
                List.of(new Department(2L, "開発部", 1L))
        );

        mockMvc.perform(get("/departments/search")
                .param("keyword", "開発"))
                .andExpect(status().isOk());
    }
}