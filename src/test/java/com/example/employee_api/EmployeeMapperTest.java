package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//軽量用　H2
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/**
 * EmployeeMapper のテストクラス（MySQL使用）
 *
 * @SpringBootTest:
 *  - アプリ全体を起動（Controller / Service / Mapper / DB）
 *  - application.properties の MySQL 設定をそのまま使用
 *
 * @Transactional:
 *  - テスト終了後に自動ロールバック
 *  - DBにデータが残らない（安全にテストできる）
 */
@SpringBootTest
@Transactional
class EmployeeMapperTest {

    /**
     * Spring が自動で Mapper を注入
     * （MyBatis が実装クラスを生成してくれる）
     */
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 全件取得テスト
     */
    @Test
    void findAllで社員一覧を取得できる() {

        // --- 実行 ---
        List<Employee> employees = employeeMapper.findAll();

        // --- 検証 ---
        // null ではないこと
        assertNotNull(employees, "社員一覧が null");

        // 0件以上（空でもOK）
        assertTrue(employees.size() >= 0, "件数が不正");
    }

    /**
     * 追加（insert）テスト
     */
    @Test
    void insertで社員を追加できる() {

        // --- 準備 ---
        Employee emp = new Employee();
        emp.setName("テスト太郎");
        emp.setDepartment("IT");

        // --- 実行 ---
        employeeMapper.insert(emp);

        // --- 検証 ---
        List<Employee> list = employeeMapper.findAll();

        // 追加されたデータが存在するか
        boolean exists = list.stream()
                .anyMatch(e -> "テスト太郎".equals(e.getName()));

        assertTrue(exists, "追加データが存在しない");
    }

    /**
     * 更新（update）テスト
     */
    @Test
    void updateで社員を更新できる() {

        // --- 準備（先に1件追加） ---
        Employee emp = new Employee();
        emp.setName("更新前");
        emp.setDepartment("IT");
        employeeMapper.insert(emp);

        // --- 取得 ---
        List<Employee> list = employeeMapper.findAll();
        Employee target = list.get(list.size() - 1);

        // --- 更新 ---
        target.setName("更新後");
        employeeMapper.update(target);

        // --- 再取得 ---
        Employee updated = employeeMapper.findById(target.getId());

        // --- 検証 ---
        assertEquals("更新後", updated.getName(), "更新されていない");
    }

    /**
     * 削除（delete）テスト
     */
    @Test
    void deleteで社員を削除できる() {

        // --- 準備 ---
        Employee emp = new Employee();
        emp.setName("削除対象");
        emp.setDepartment("IT");
        employeeMapper.insert(emp);

        // --- 取得 ---
        List<Employee> list = employeeMapper.findAll();
        Employee target = list.get(list.size() - 1);

        // --- 削除 ---
        employeeMapper.delete(target.getId());

        // --- 再取得 ---
        Employee deleted = employeeMapper.findById(target.getId());

        // --- 検証 ---
        assertNull(deleted, "削除されていない");
    }

    /**
     * 検索テスト
     */
    @Test
    void searchで条件検索できる() {

        // --- 準備 ---
        Employee emp = new Employee();
        emp.setName("検索太郎");
        emp.setDepartment("営業");
        employeeMapper.insert(emp);

        // --- 実行 ---
        List<Employee> result = employeeMapper.search("検索", "営業");

        // --- 検証 ---
        assertFalse(result.isEmpty(), "検索結果が空");

        boolean found = result.stream()
                .anyMatch(e -> "検索太郎".equals(e.getName()));

        assertTrue(found, "検索対象が見つからない");
    }
}