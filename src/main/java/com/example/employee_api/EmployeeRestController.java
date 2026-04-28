package com.example.employee_api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社員情報のREST APIコントローラ
 *
 * ・AngularなどのフロントエンドからHTTPで呼ばれる
 * ・JSON形式でデータをやり取りする
 * ・Service層を呼び出して業務処理を実行
 */
@CrossOrigin(origins = "http://localhost:4200")
// ↑ Angular（開発サーバー）からのアクセスを許可（CORS対策）

@RestController
// ↑ REST API用コントローラ（戻り値はJSONになる）

@RequestMapping("/employee-api/employees")
// ↑ URLの共通パス（例: /employees, /employees/1）
public class EmployeeRestController {

    /**
     * サービスクラス（業務ロジック）
     */
    private final EmployeeService employeeService;

    /**
     * コンストラクタインジェクション
     *
     * ・Springが自動でEmployeeServiceを注入する
     */
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 社員一覧取得
     *
     * GET /employees
     *
     * @return 社員リスト（JSON）
     */
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    /**
     * 社員1件取得
     *
     * GET /employees/{id}
     *
     * @param id 社員ID
     * @return Employee（JSON）
     */
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    /**
     * 社員登録
     *
     * POST /employees
     *
     * @param employee リクエストボディ（JSON）
     *
     * @RequestBody:
     * ・JSON → Javaオブジェクトに変換
     */
    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
    }

    /**
     * 社員更新
     *
     * PUT /employees/{id}
     *
     * @param id URLのID
     * @param employee 更新データ（JSON）
     *
     * ※ URLのidを優先して設定（安全対策）
     */
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.update(employee);
    }

    /**
     * 社員削除
     *
     * DELETE /employees/{id}
     *
     * @param id 削除対象ID
     */
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    /**
     * 条件検索（部分一致）
     *
     * GET /employees/search?keyword=xxx&department=yyy
     *
     * @param keyword 名前検索（任意）
     * @param department 部署検索（任意）
     *
     * @RequestParam(required = false):
     * ・パラメータが無くてもOK
     */
    @GetMapping("/search")
    public List<Employee> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String department) {
        return employeeService.search(keyword, department);
    }
}