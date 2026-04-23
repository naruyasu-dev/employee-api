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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * EmployeeRestController のテストクラス
 *
 * @WebMvcTest:
 *  - Controller 層だけをテストする
 *  - Service / Mapper / DB は本物を使わない
 */
@WebMvcTest(EmployeeRestController.class)
class EmployeeRestControllerTest {

    /**
     * 擬似HTTPリクエストを送るためのオブジェクト
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Controller が依存している Service をモック化
     */
    @MockBean
    private EmployeeService employeeService;

    /**
     * MyBatis の Mapper が起動しないようにするためのモック
     *
     * ※ 環境によっては無くても動くが、
     *    あなたの環境ではこれを入れておく方が安全
     */
    @MockBean
    private EmployeeMapper employeeMapper;

    /**
     * 一覧取得テスト
     * GET /employees
     */
    @Test
    void findAllで社員一覧をJSONで取得できる() throws Exception {

        // --- 準備 ---
        List<Employee> employees = List.of(
                new Employee(1L, "太郎", "IT"),
                new Employee(2L, "花子", "営業")
        );

        when(employeeService.findAll()).thenReturn(employees);

        // --- 実行 & 検証 ---
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("太郎"))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("花子"))
                .andExpect(jsonPath("$[1].department").value("営業"));

        verify(employeeService).findAll();
    }

    /**
     * 1件取得テスト
     * GET /employees/{id}
     */
    @Test
    void findByIdで社員1件を取得できる() throws Exception {

        // --- 準備 ---
        Employee employee = new Employee(1L, "太郎", "IT");
        when(employeeService.findById(1L)).thenReturn(employee);

        // --- 実行 & 検証 ---
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("太郎"))
                .andExpect(jsonPath("$.department").value("IT"));

        verify(employeeService).findById(1L);
    }

    /**
     * 追加テスト
     * POST /employees
     *
     * Service の add() は void のため、
     * HTTP 200 だけ確認する
     */
    @Test
    void addで社員を追加できる() throws Exception {

        // --- 準備 ---
        doNothing().when(employeeService).add(any(Employee.class));

        // --- 実行 & 検証 ---
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "新規太郎",
                          "department": "IT"
                        }
                        """))
                .andExpect(status().isOk());

        verify(employeeService).add(any(Employee.class));
    }

    /**
     * 更新テスト
     * PUT /employees/{id}
     *
     * Service の update() は void のため、
     * HTTP 200 だけ確認する
     */
    @Test
    void updateで社員を更新できる() throws Exception {

        // --- 準備 ---
        doNothing().when(employeeService).update(any(Employee.class));

        // --- 実行 & 検証 ---
        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "id": 1,
                          "name": "更新後",
                          "department": "営業"
                        }
                        """))
                .andExpect(status().isOk());

        verify(employeeService).update(any(Employee.class));
    }

    /**
     * 削除テスト
     * DELETE /employees/{id}
     */
    @Test
    void deleteで社員を削除できる() throws Exception {

        // --- 準備 ---
        doNothing().when(employeeService).delete(1L);

        // --- 実行 & 検証 ---
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());

        verify(employeeService).delete(1L);
    }

    /**
     * 存在しないIDの取得テスト
     * GET /employees/{id}
     *
     * Service が null を返した場合、
     * Controller がそのまま返すなら空レスポンスになる
     */
    @Test
    void findByIdで存在しない社員の場合は空レスポンスになる() throws Exception {

        // --- 準備 ---
        when(employeeService.findById(999L)).thenReturn(null);

        // --- 実行 & 検証 ---
        mockMvc.perform(get("/employees/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(employeeService).findById(999L);
    }
}