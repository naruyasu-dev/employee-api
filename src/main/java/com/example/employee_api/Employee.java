package com.example.employee_api;

/**
 * 社員エンティティ
 */
public class Employee {

    private Long id;
    private String name;
    private String department;

    // デフォルトコンストラクタ（必須）
    public Employee() {
    }

    // ⭐ これを追加（今回のポイント）
    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // getter / setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}