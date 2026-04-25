package com.example.employee_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社員情報を表すクラス（エンティティ / モデル）
 *
 * ・DBの employee テーブルと対応
 * ・MyBatis でのデータ受け渡しに使用
 * ・REST API の JSON としても利用される
 */
@Data
// ↑ getter / setter / toString / equals / hashCode を自動生成

@NoArgsConstructor
// ↑ 引数なしコンストラクタを自動生成
// ・MyBatis / Jackson（JSON変換）で必要

@AllArgsConstructor
// ↑ 全フィールドのコンストラクタを自動生成
// ・テストコードや初期データ作成で便利
public class Employee {

    /**
     * 社員ID（主キー）
     * ・DBでは BIGINT 型
     * ・Javaでは Long 型で扱う
     */
    private Long id;

    /**
     * 社員名
     */
    private String name;

    /**
     * 部署名
     */
    private String department;
}