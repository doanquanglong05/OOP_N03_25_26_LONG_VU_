package com.quanly.quanly_dangky.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseClass {
    private String classId;
    private Course course;
    private String teacherName;
    private String schedule;
    private int maxStudents;

    private List<Student> students = new ArrayList<>();

    public CourseClass() {}

    public CourseClass(String classId, Course course, String teacherName, String schedule, int maxStudents) {
        this.classId = classId;
        this.course = course;
        this.teacherName = teacherName;
        this.schedule = schedule;
        this.maxStudents = maxStudents;
    }

    // Getter & Setter
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

    // Thêm sinh viên vào lớp
    public boolean addStudent(Student student) {
        // Kiểm tra nếu sinh viên đã có trong lớp thì không thêm nữa
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }
}
