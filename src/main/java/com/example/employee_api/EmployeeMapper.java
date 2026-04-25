package com.example.employee_api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Employee テーブルに対するデータアクセス用インターフェース（Mapper）
 *
 * ・MyBatisが実装クラスを自動生成する
 * ・SQLは対応する XML（EmployeeMapper.xml）に記述する
 * ・Service層から呼び出される
 */
//@Mapper
// ↑ MyBatisのMapperとして認識させるアノテーション
// ※ @MapperScan があれば省略可能(EmployeeApiApplication)
public interface EmployeeMapper {

    /**
     * 全件取得
     *
     * ・employee テーブルの全レコードを取得
     * ・XMLの <select id="findAll"> に対応
     */
    List<Employee> findAll();

    /**
     * IDで1件取得
     *
     * @param id 社員ID
     * @return Employee（該当なしの場合は null）
     *
     * ・XMLの <select id="findById"> に対応
     */
    Employee findById(Long id);

    /**
     * 登録（INSERT）
     *
     * @param employee 登録する社員情報
     *
     * ・XMLの <insert id="insert"> に対応
     */
    void insert(Employee employee);

    /**
     * 更新（UPDATE）
     *
     * @param employee 更新する社員情報
     *
     * ・XMLの <update id="update"> に対応
     */
    void update(Employee employee);

    /**
     * 削除（DELETE）
     *
     * @param id 削除対象の社員ID
     *
     * ・XMLの <delete id="delete"> に対応
     */
    void delete(Long id);

    /**
     * 条件検索（部分一致）
     *
     * @param keyword 名前の検索キーワード（部分一致）
     * @param department 部署名（部分一致）
     * @return 該当する社員リスト
     *
     * ・XMLの <select id="search"> に対応
     *
     * @Param:
     * ・複数引数をXML側で名前指定するために必要
     * ・#{keyword}, #{department} として使用可能
     */
    List<Employee> search(
            @Param("keyword") String keyword,
            @Param("department") String department
    );
}