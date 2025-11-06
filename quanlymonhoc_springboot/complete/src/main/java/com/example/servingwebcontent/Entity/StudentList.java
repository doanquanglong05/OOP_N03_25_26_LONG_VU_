/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentList (Spring Boot version)
 * - Dùng để bọc danh sách sinh viên (tương thích với mã cũ từng dùng XML)
 * - Hoàn toàn in-memory, không phụ thuộc FileUtils hay XML
 * - Có thể dùng làm DTO để trao đổi dữ liệu giữa controller/service
 */
public class StudentList implements Serializable {

    private List<Student> students = new ArrayList<>();

    // ===== Constructors =====
    public StudentList() {}

    public StudentList(List<Student> students) {
        this.students = (students != null) ? students : new ArrayList<>();
    }

    // ===== Getters & Setters =====
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = (students != null) ? students : new ArrayList<>();
    }

    // ===== Tiện ích =====
    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    public void clear() {
        students.clear();
    }

    @Override
    public String toString() {
        return "StudentList{total=" + students.size() + "}";
    }
}
