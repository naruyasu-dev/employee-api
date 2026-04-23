package com.example.employee_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Boot アプリ全体が正常に起動するか確認するテストクラス
 *
 * @SpringBootTest:
 *  - Spring Boot アプリケーションを本番に近い形で起動する
 *  - Controller / Service / Mapper / DataSource / 設定ファイルなどを読み込む
 *
 * このテストの目的:
 *  - アプリ起動時にエラーが出ないことを確認する
 *  - Bean定義ミスや設定ミスがないことを確認する
 */
@SpringBootTest
class EmployeeApiApplicationTest {

    /**
     * アプリケーションコンテキストが正常にロードできるかを確認するテスト
     *
     * contextLoads():
     *  - Spring Boot の標準的な起動確認テスト
     *  - 例外なく起動できれば成功
     *
     * ※ assert は不要
     *    起動中にエラーが出なければテスト成功になる
     */
    @Test
    void contextLoads() {
    }
}