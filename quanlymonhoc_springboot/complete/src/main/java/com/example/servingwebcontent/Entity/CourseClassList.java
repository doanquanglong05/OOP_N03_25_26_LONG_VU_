/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseClassList (Spring Boot version)
 * - Dùng để bọc danh sách lớp học phần (tương thích với mã cũ từng dùng XML)
 * - Không còn liên quan tới FileUtils hay XML nữa
 * - Có thể dùng tạm như DTO hoặc lớp trung gian để trao đổi dữ liệu
 */
public class CourseClassList implements Serializable {

    private List<CourseClass> courseClasses = new ArrayList<>();

    // ===== Constructors =====
    public CourseClassList() {}

    public CourseClassList(List<CourseClass> courseClasses) {
        this.courseClasses = (courseClasses != null) ? courseClasses : new ArrayList<>();
    }

    // ===== Getters & Setters =====
    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseClasses(List<CourseClass> courseClasses) {
        this.courseClasses = (courseClasses != null) ? courseClasses : new ArrayList<>();
    }

    // ===== Tiện ích =====
    public void addCourseClass(CourseClass courseClass) {
        if (courseClass != null) {
            courseClasses.add(courseClass);
        }
    }

    public void clear() {
        courseClasses.clear();
    }

    @Override
    public String toString() {
        return "CourseClassList{total=" + courseClasses.size() + "}";
    }
}
