package com.example.employee_api.model;

/**
 * 社員情報を保持するモデルクラス
 */
public class Employee {

    /**
     * 社員ID
     */
    private Long id;

    /**
     * 社員名
     */
    private String name;

    /**
     * 部署ID
     *
     * employee.department_id に対応する。
     */
    private Long departmentId;

    /**
     * 部署名
     *
     * department_master.name をJOINして取得する表示用項目。
     */
    private String departmentName;

    public Employee() {
    }

    public Employee(Long id, String name, Long departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}