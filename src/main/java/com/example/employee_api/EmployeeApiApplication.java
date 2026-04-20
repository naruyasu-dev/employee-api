package com.example.employee_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 外部 Tomcat から起動できるようにする
@SpringBootApplication
public class EmployeeApiApplication extends SpringBootServletInitializer {

	// Tomcat がこの Spring Boot アプリを見つけて起動するための設定
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EmployeeApiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApiApplication.class, args);
    }
}