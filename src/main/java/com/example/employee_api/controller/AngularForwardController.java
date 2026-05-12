package com.example.employee_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularForwardController {

    @GetMapping({
            "/employee-ui",
            "/employee-ui/",
            "/employee-ui/employees",
            "/employee-ui/about"
    })
    public String forwardAngularRoutes() {
        return "forward:/employee-ui/index.html";
    }
}