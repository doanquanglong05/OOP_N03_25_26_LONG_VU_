/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseList (Spring Boot version)
 * - Dùng để bọc danh sách các môn học (tương thích mã cũ từng dùng XML)
 * - Không còn liên quan tới FileUtils/XML, chỉ lưu trong bộ nhớ
 * - Có thể dùng như DTO trung gian khi trao đổi dữ liệu giữa controller/service
 */
public class CourseList implements Serializable {

    private List<Course> courses = new ArrayList<>();

    // ===== Constructors =====
    public CourseList() {}

    public CourseList(List<Course> courses) {
        this.courses = (courses != null) ? courses : new ArrayList<>();
    }

    // ===== Getters & Setters =====
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = (courses != null) ? courses : new ArrayList<>();
    }

    // ===== Tiện ích =====
    public void addCourse(Course course) {
        if (course != null) {
            courses.add(course);
        }
    }

    public void clear() {
        courses.clear();
    }

    @Override
    public String toString() {
        return "CourseList{total=" + courses.size() + "}";
    }
}
