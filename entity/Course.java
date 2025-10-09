package com.quanly.quanly_dangky.entity;

public class Course {
    private String courseId;
    private String courseName;
    private int credit;
    private String description;

    public Course() {}

    public Course(String courseId, String courseName, int credit, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.description = description;
    }

    // Getter
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getCredit() { return credit; }
    public String getDescription() { return description; }

    // Setter
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCredit(int credit) { this.credit = credit; }
    public void setDescription(String description) { this.description = description; }
}
