/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.*;
import java.util.stream.Collectors;
import Entity.*;

public class EnrollmentController {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<CourseClass> courseClasses = new ArrayList<>();

    public void addStudent(String id, String name, String gender, int yob, String email) {
        students.add(new Student(id, name, gender, yob, email));
    }

    public void addCourse(String id, String name, int credit, String desc) {
        courses.add(new Course(id, name, credit, desc));
    }

    public boolean createClass(String classId, String courseId, String teacher, String schedule, int max) {
        Optional<Course> course = courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst();
        if (course.isPresent()) {
            courseClasses.add(new CourseClass(classId, course.get(), teacher, schedule, max));
            return true;
        }
        return false;
    }

    public boolean assignStudentToClass(String studentId, String classId) {
        Optional<Student> student = students.stream().filter(s -> s.getId().equals(studentId)).findFirst();
        Optional<CourseClass> courseClass = courseClasses.stream().filter(c -> c.getClassId().equals(classId)).findFirst();
        if (student.isPresent() && courseClass.isPresent()) {
            return courseClass.get().addStudent(student.get());
        }
        return false;
    }

    public List<String> reportClasses() {
        List<String> result = new ArrayList<>();
        for (CourseClass cc : courseClasses) {
            result.add(String.format("Lớp %s (%s): %d/%d SV - GV: %s",
                cc.getClassId(), cc.getCourse().getCourseName(),
                cc.getStudents().size(), cc.getMaxStudents(),
                cc.getTeacherName()));
        }
        return result;
    }

    public List<Student> searchStudentByName(String keyword) {
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudents() { return students; }
    public List<Course> getCourses() { return courses; }
    public List<CourseClass> getCourseClasses() { return courseClasses; }
}
