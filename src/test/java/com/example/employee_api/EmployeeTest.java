package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Employee クラスのテスト
 *
 * ・データ保持クラス（POJO）のため
 *   主に getter / setter / コンストラクタを確認する
 */
class EmployeeTest {

    /**
     * 引数ありコンストラクタのテスト
     */
    @Test
    void コンストラクタで値が正しく設定される() {

        // --- 実行 ---
        Employee emp = new Employee(1L, "太郎", "IT");

        // --- 検証 ---
        assertEquals(1L, emp.getId());
        assertEquals("太郎", emp.getName());
        assertEquals("IT", emp.getDepartment());
    }

    /**
     * setter / getter のテスト
     */
    @Test
    void setterとgetterが正しく動作する() {

        // --- 準備 ---
        Employee emp = new Employee();

        // --- 実行 ---
        emp.setId(2L);
        emp.setName("花子");
        emp.setDepartment("営業");

        // --- 検証 ---
        assertEquals(2L, emp.getId());
        assertEquals("花子", emp.getName());
        assertEquals("営業", emp.getDepartment());
    }

    /**
     * null 値の扱いのテスト（任意）
     */
    @Test
    void null値も設定できる() {

        // --- 実行 ---
        Employee emp = new Employee();
        emp.setName(null);

        // --- 検証 ---
        assertNull(emp.getName());
    }
}