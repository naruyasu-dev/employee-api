package com.example.employee_api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularForwardController {

    @RequestMapping({
            "/employee-ui",
            "/employee-ui/",
            "/employee-ui/employees",
            "/employee-ui/about"
    })
    public String forwardAngularRoutes() {
        return "forward:/employee-ui/index.html";
    }
}