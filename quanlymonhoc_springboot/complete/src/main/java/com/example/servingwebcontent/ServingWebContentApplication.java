package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ServingWebContentApplication
 * ----------------------------
 * Đây là class khởi động (entry point) của toàn bộ ứng dụng Spring Boot.
 * 
 * - Annotation @SpringBootApplication:
 *   • Bao gồm 3 annotation chính:
 *       @Configuration  -> cho phép định nghĩa các bean cấu hình
 *       @EnableAutoConfiguration -> Spring tự động cấu hình các thành phần (Tomcat, Thymeleaf, v.v)
 *       @ComponentScan -> quét toàn bộ package con của "com.example.servingwebcontent"
 * 
 * - Hàm main(): chạy Spring Boot, khởi tạo Tomcat và load toàn bộ controller, entity, utils
 * 
 * ➤ Khi chạy: http://localhost:8080/
 *   Spring Boot sẽ load toàn bộ các controller trong package:
 *       com.example.servingwebcontent.Controller
 *   và render các view (Thymeleaf) trong:
 *       src/main/resources/templates/
 */
@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        // Chạy ứng dụng Spring Boot
        SpringApplication.run(ServingWebContentApplication.class, args);
        System.out.println("✅ Ứng dụng Quản lý Đăng ký Môn học đã khởi động tại http://localhost:8080/");
    }
}
