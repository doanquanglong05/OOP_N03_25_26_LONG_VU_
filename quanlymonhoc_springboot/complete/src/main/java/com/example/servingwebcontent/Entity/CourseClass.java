/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseClass entity (Spring Boot compatible)
 * - Lưu trữ lớp học phần cùng danh sách sinh viên đã đăng ký.
 * - Hoạt động hoàn toàn in-memory, không dùng XML.
 */
public class CourseClass implements Serializable {

    private String classId;
    private Course course;
    private String teacherName;
    private String schedule;
    private int maxStudents;

    private List<Student> students = new ArrayList<>();

    // ===== Constructors =====
    public CourseClass() {}

    public CourseClass(String classId, Course course, String teacherName, String schedule, int maxStudents) {
        this.classId = classId;
        this.course = course;
        this.teacherName = teacherName;
        this.schedule = schedule;
        this.maxStudents = maxStudents;
    }

    // ===== Logic xử lý chính =====
    /**
     * Thêm sinh viên vào lớp nếu chưa có và lớp chưa đầy
     * @param student đối tượng sinh viên
     * @return true nếu thêm thành công, false nếu trùng hoặc lớp đầy
     */
    public boolean addStudent(Student student) {
        if (student == null) return false;

        // Kiểm tra sinh viên đã tồn tại trong lớp chưa
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false; // Sinh viên đã đăng ký
            }
        }

        // Kiểm tra số lượng tối đa
        if (students.size() >= maxStudents) {
            return false; // Lớp đầy
        }

        return students.add(student);
    }

    // ===== Getters & Setters =====
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    // ===== Tiện ích in log =====
    @Override
    public String toString() {
        return String.format(
            "CourseClass[id=%s, course=%s, teacher=%s, schedule=%s, max=%d, current=%d]",
            classId,
            course != null ? course.getCourseName() : "null",
            teacherName,
            schedule,
            maxStudents,
            students.size()
        );
    }
}
