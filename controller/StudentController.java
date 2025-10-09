package com.quanly.quanly_dangky.controller;

import com.quanly.quanly_dangky.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", List.of(
                new Student("S001", "Doan Quang Long", "a@example.com"),
                new Student("S002", "Doan Hoang Vu", "b@example.com"),
                new Student("S003", "Le Van C", "c@example.com")
        ));
        return "students"; // -> templates/students.html
    }
}
