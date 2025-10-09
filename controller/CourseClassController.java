package com.quanly.quanly_dangky.controller;

import com.quanly.quanly_dangky.entity.Course;
import com.quanly.quanly_dangky.entity.CourseClass;
import com.quanly.quanly_dangky.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseClassController {

    private final List<CourseClass> classes = new ArrayList<>();

    @PostConstruct
    void init() {
        Course c1 = new Course("IT101","Intro to IT",3,"Basics of IT");
        CourseClass cls1 = new CourseClass("CL001", c1, "Mr. Long", "Mon 7-9", 40);
        cls1.addStudent(new Student("S001","Nguyen Van A","a@example.com"));
        classes.add(cls1);
    }

    @GetMapping("/classes")
    public String listClasses(Model model) {
        model.addAttribute("classes", classes);
        return "classes"; // -> templates/classes.html
    }
}
