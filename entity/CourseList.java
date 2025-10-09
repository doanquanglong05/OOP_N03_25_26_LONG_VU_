package com.quanly.quanly_dangky.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseList {
    private List<Course> courses = new ArrayList<>();

    public CourseList() {}

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Thêm 1 tiện ích: thêm course vào list
    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
