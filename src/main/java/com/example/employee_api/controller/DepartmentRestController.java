package com.example.employee_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_api.model.Department;
import com.example.employee_api.service.DepartmentService;

/**
 * 部署管理REST API
 *
 * AngularなどのフロントエンドからHTTPで呼び出される。
 */
@RestController
@RequestMapping("/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    /**
     * コンストラクタインジェクション
     *
     * SpringがDepartmentServiceを自動で注入する。
     */
    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 部署一覧取得API
     *
     * GET /departments
     *
     * @return 部署一覧
     */
    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    /**
     * 部署詳細取得API
     *
     * GET /departments/{id}
     *
     * @param id 部署ID
     * @return 部署情報
     */
    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    /**
     * 部署登録API
     *
     * POST /departments
     *
     * @param department 部署情報
     */
    @PostMapping
    public void add(@RequestBody Department department) {
        departmentService.add(department);
    }

    /**
     * 部署更新API
     *
     * PUT /departments/{id}
     *
     * @param id 部署ID
     * @param department 部署情報
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Department department) {
        department.setId(id);
        departmentService.update(department);
    }

    /**
     * 部署削除API
     *
     * DELETE /departments/{id}
     *
     * @param id 部署ID
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }

    /**
     * 部署検索API
     *
     * GET /departments/search?keyword=開発
     *
     * @param keyword 検索キーワード
     * @return 部署一覧
     */
    @GetMapping("/search")
    public List<Department> search(@RequestParam(required = false) String keyword) {
        return departmentService.search(keyword);
    }
}