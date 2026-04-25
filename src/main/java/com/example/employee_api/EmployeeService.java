package com.example.employee_api;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 社員情報の業務ロジックを扱うサービスクラス
 *
 * ・Controller と Mapper の仲介役
 * ・トランザクション管理を担当
 * ・業務ルールがあればここに書く
 */
@Service
// ↑ Springに「サービスクラス」として登録される

@Transactional
// ↑ このクラス内の処理はすべてトランザクション対象
// ・成功 → コミット
// ・例外 → ロールバック
public class EmployeeService {

    /**
     * データアクセス（MyBatis Mapper）
     */
    private final EmployeeMapper employeeMapper;

    /**
     * コンストラクタインジェクション
     *
     * ・Springが自動でEmployeeMapperを注入する
     */
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 社員一覧取得
     *
     * @return 全社員リスト
     */
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    /**
     * 社員1件取得
     *
     * @param id 社員ID
     * @return 該当社員（存在しない場合はnull）
     */
    public Employee findById(Long id) {
        return employeeMapper.findById(id);
    }

    /**
     * 社員登録
     *
     * @param employee 登録する社員情報
     */
    public void add(Employee employee) {
        employeeMapper.insert(employee);
    }

    /**
     * 社員更新
     *
     * @param employee 更新する社員情報
     */
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    /**
     * 社員削除
     *
     * @param id 削除対象ID
     */
    public void delete(Long id) {
        employeeMapper.delete(id);
    }

    /**
     * 条件検索（部分一致）
     *
     * @param keyword 名前キーワード（任意）
     * @param department 部署（任意）
     * @return 検索結果リスト
     */
    public List<Employee> search(String keyword, String department) {
        return employeeMapper.search(keyword, department);
    }
}