package com.example.employee_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SPA画面用のForward Controller。
 *
 * Angular / React / Vue などのSPAをTomcat配下で公開した場合、
 * /employee-ui/employees のような画面URLに直接アクセスすると、
 * サーバー側では実ファイルが存在しないため 404 になる。
 *
 * そのため、SPAの画面URLを /employee-ui/index.html にforwardし、
 * 実際の画面表示はブラウザ側のSPAルーターに任せる。
 */
@Controller
public class SpaForwardController {

    @RequestMapping({
        "/employee-ui",
        "/employee-ui/",
        "/employee-ui/employees",
        "/employee-ui/about"
    })
    public String forwardToSpa() {
        return "forward:/employee-ui/index.html";
    }
}