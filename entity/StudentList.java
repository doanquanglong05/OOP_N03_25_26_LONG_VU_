package com.quanly.quanly_dangky.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private List<Student> students = new ArrayList<>();

    public StudentList() {}

    public StudentList(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}
