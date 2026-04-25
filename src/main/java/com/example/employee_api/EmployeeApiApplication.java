package com.example.employee_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * アプリケーションのエントリポイント（起動クラス）
 *
 * ・Spring Boot の起動
 * ・MyBatis Mapper の自動スキャン
 * ・外部Tomcat（WARデプロイ）対応
 */
@SpringBootApplication
// ↑ Spring Boot の基本設定（以下をまとめて有効化）
// ・@Configuration（設定クラス）
// ・@EnableAutoConfiguration（自動設定）
// ・@ComponentScan（コンポーネントスキャン）

@MapperScan("com.example.employee_api")
// ↑ MyBatis の Mapper インターフェースを自動検出
// ・@Mapper を付けたインターフェースを探す
// ・Mapper XML と紐づける

public class EmployeeApiApplication extends SpringBootServletInitializer {

    /**
     * メインメソッド（単体起動用）
     *
     * ・jar 実行時にここから起動される
     * ・Spring Boot のアプリケーションを立ち上げる
     *
     * 実行例:
     * java -jar employee-api.jar
     */
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApiApplication.class, args);
    }

    /**
     * 外部Tomcatデプロイ用設定（WAR用）
     *
     * ・WARファイルとしてデプロイする場合に必要
     * ・Tomcatなどのサーブレットコンテナから起動される
     *
     * 対象:
     * ・Jenkins + Tomcat
     * ・外部TomcatにWAR配置
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EmployeeApiApplication.class);
    }
}