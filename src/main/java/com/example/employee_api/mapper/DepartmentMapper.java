package com.example.employee_api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_api.model.Department;

/**
 * 部署情報を操作するMyBatis Mapper
 *
 * department テーブルに対する
 * SELECT / INSERT / UPDATE / DELETE を定義する。
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 部署一覧を取得する
     *
     * @return 部署一覧
     */
    List<Department> findAll();

    /**
     * 部署IDを指定して1件取得する
     *
     * @param id 部署ID
     * @return 部署情報
     */
    Department findById(Long id);

    /**
     * 部署を登録する
     *
     * @param department 部署情報
     */
    void insert(Department department);

    /**
     * 部署を更新する
     *
     * @param department 部署情報
     */
    void update(Department department);

    /**
     * 部署を削除する
     *
     * @param id 部署ID
     */
    void delete(Long id);

    /**
     * 部署名で検索する
     *
     * @param keyword 検索キーワード
     * @return 部署一覧
     */
    List<Department> search(@Param("keyword") String keyword);
}