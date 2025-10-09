package com.quanly.quanly_dangky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EnrollmentController {

    @GetMapping("/enrollments")
    public String listEnrollments(Model model) {
        List<String> data = List.of(
                "Nguyen Van A -> Intro to IT",
                "Tran Thi B  -> Database",
                "Le Van C    -> Networking"
        );
        model.addAttribute("enrollments", data);
        return "enrollments"; // -> templates/enrollments.html
    }
}
