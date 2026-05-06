package com.example.employee_api;

/**
 * 社員情報を保持するモデルクラス
 *
 * employee テーブルの1レコードに対応する。
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
     * 部署名
     *
     * 旧項目。
     * 今後は departmentId を使用する。
     */
    private String department;

    /**
     * 部署ID
     *
     * department テーブルの id に対応する。
     */
    private Long departmentId;

    /**
     * デフォルトコンストラクタ
     */
    public Employee() {
    }

    /**
     * 旧形式のコンストラクタ
     *
     * 既存テストや既存コードとの互換性のため残す。
     */
    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    /**
     * 新形式のコンストラクタ
     *
     * departmentId を使用するERP風の形式。
     */
    public Employee(Long id, String name, String department, Long departmentId) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.departmentId = departmentId;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}