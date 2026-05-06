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
     * 旧部署名
     *
     * 旧項目。
     * 最終的には employee テーブルから削除予定。
     */
    private String department;

    /**
     * 部署ID
     *
     * employee.department_id に対応する。
     */
    private Long departmentId;

    /**
     * 部署マスタ上の部署名
     *
     * department.name を JOIN して取得する表示用項目。
     */
    private String departmentName;

    /**
     * デフォルトコンストラクタ
     */
    public Employee() {
    }

    /**
     * 旧形式のコンストラクタ
     *
     * 既存テスト・既存処理との互換性のため残す。
     */
    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    /**
     * departmentId 対応コンストラクタ
     */
    public Employee(Long id, String name, String department, Long departmentId) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.departmentId = departmentId;
    }

    /**
     * departmentName 対応コンストラクタ
     */
    public Employee(Long id, String name, String department, Long departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.department = department;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}