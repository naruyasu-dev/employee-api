package com.example.employee_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS設定クラス
 *
 * React + Vite 開発サーバーから
 * Spring Boot API へアクセスできるようにする。
 *
 * React開発URL:
 *   http://localhost:5173
 *
 * Vue開発URL:
 *   http://localhost:4200
 *
 * API URL:
 *   http://localhost:8080/employee-api/employees
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://127.0.0.1:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
