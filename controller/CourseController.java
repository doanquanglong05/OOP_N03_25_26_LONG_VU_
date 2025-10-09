package com.quanly.quanly_dangky.controller;

import com.quanly.quanly_dangky.entity.Course;
import com.quanly.quanly_dangky.entity.CourseList;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    private final CourseList courseList = new CourseList();

    @PostConstruct
    void init() {
        // dữ liệu demo
        courseList.addCourse(new Course("IT101", "Intro to IT", 3, "Basics of IT"));
        courseList.addCourse(new Course("DB201", "Database", 3, "SQL & Relational"));
        courseList.addCourse(new Course("NW301", "Networking", 3, "TCP/IP fundamentals"));
    }

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseList.getCourses());
        return "courses"; // -> templates/courses.html
    }
}
