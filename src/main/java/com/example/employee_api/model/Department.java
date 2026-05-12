package com.example.employee_api.model;

/**
 * 部署情報を保持するモデルクラス
 *
 * department テーブルの1レコードに対応する。
 */
public class Department {

    /**
     * 部署ID
     */
    private Long id;

    /**
     * 部署名
     */
    private String name;

    /**
     * 親部署ID
     *
     * 例：
     * 本社       → parentId = null
     * 開発部     → parentId = 1
     */
    private Long parentId;

    /**
     * デフォルトコンストラクタ
     *
     * MyBatis が Department オブジェクトを作るときに使用する。
     */
    public Department() {
    }

    /**
     * 全項目コンストラクタ
     */
    public Department(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}