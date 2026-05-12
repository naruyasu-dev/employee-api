package com.example.employee_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee_api.mapper.DepartmentMapper;
import com.example.employee_api.model.Department;

/**
 * 部署情報の業務処理を行うServiceクラス
 *
 * Controllerから呼び出され、
 * DepartmentMapperを使ってdepartmentテーブルを操作する。
 */
@Service
@Transactional
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    /**
     * コンストラクタインジェクション
     *
     * SpringがDepartmentMapperを自動で注入する。
     */
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 部署一覧を取得する
     *
     * @return 部署一覧
     */
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    /**
     * 部署IDを指定して1件取得する
     *
     * @param id 部署ID
     * @return 部署情報
     */
    public Department findById(Long id) {
        return departmentMapper.findById(id);
    }

    /**
     * 部署を登録する
     *
     * @param department 部署情報
     */
    public void add(Department department) {
        departmentMapper.insert(department);
    }

    /**
     * 部署を更新する
     *
     * @param department 部署情報
     */
    public void update(Department department) {
        departmentMapper.update(department);
    }

    /**
     * 部署を削除する
     *
     * @param id 部署ID
     */
    public void delete(Long id) {
        departmentMapper.delete(id);
    }

    /**
     * 部署名で検索する
     *
     * @param keyword 検索キーワード
     * @return 部署一覧
     */
    public List<Department> search(String keyword) {
        return departmentMapper.search(keyword);
    }
}