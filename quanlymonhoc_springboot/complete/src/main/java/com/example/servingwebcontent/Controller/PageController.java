package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/students")
    public String students() {
        return "students"; // trỏ đến templates/students.html
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }

    @GetMapping("/classes")
    public String classes() {
        return "classes";
    }

    @GetMapping("/enrollments")
    public String enrollments() {
        return "enrollments";
    }
}
