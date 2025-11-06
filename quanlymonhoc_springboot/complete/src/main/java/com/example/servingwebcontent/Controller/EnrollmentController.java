/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Controller;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import com.example.servingwebcontent.Entity.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * EnrollmentController - Spring Boot (no XML)
 * - Lưu trữ in-memory cho students/courses/courseClasses
 * - Giữ nguyên các hàm public cũ để phần khác có thể gọi thẳng
 * - Thêm REST endpoints để thao tác qua HTTP
 *
 * Base path: /api/enroll
 *
 * Endpoints:
 *  POST   /students         (body: StudentReq)              -> addStudent(...)
 *  POST   /courses          (body: CourseReq)               -> addCourse(...)
 *  POST   /classes          (body: ClassReq)                -> createClass(...)
 *  POST   /assign           (params: studentId,classId)     -> assignStudentToClass(...)
 *  GET    /report           -> reportClasses()
 *  GET    /students/search?q=... -> searchStudentByName(...)
 *  GET    /students         -> getStudents()
 *  GET    /courses          -> getCourses()
 *  GET    /classes          -> getCourseClasses()
 */
@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {
    // In-memory store (giống bản gốc: ArrayList)
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<CourseClass> courseClasses = new ArrayList<>();

    /* ======================= DTOs cho REST ======================= */
    public record StudentReq(String id, String name, String gender, int yob, String email) {}
    public record CourseReq(String id, String name, int credit, String desc) {}
    public record ClassReq(String classId, String courseId, String teacher, String schedule, Integer max) {}

    /* ======================= HÀM CŨ (giữ nguyên chữ ký) ======================= */

    public void addStudent(String id, String name, String gender, int yob, String email) {
        LocalDate dob = LocalDate.of(yob, 1, 1);
students.add(new Student(id, name, gender, dob, email));

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

    /* ======================= REST endpoints (bọc quanh hàm cũ) ======================= */

    @PostMapping("/students")
    public ResponseEntity<?> apiAddStudent(@RequestBody StudentReq req) {
        if (req == null || req.id() == null || req.id().isBlank())
            return ResponseEntity.badRequest().body("id is required");
        // Không chặn trùng id theo bản gốc; nếu muốn chặn thì mở comment dưới:
        // if (students.stream().anyMatch(s -> s.getId().equals(req.id()))) return ResponseEntity.badRequest().body("id exists");

        addStudent(req.id(), req.name(), req.gender(), req.yob(), req.email());
        return ResponseEntity.ok(students.stream().filter(s -> s.getId().equals(req.id())).findFirst().orElse(null));
    }

    @PostMapping("/courses")
    public ResponseEntity<?> apiAddCourse(@RequestBody CourseReq req) {
        if (req == null || req.id() == null || req.id().isBlank())
            return ResponseEntity.badRequest().body("id is required");

        addCourse(req.id(), req.name(), req.credit(), req.desc());
        return ResponseEntity.ok(courses.stream().filter(c -> c.getCourseId().equals(req.id())).findFirst().orElse(null));
    }

    @PostMapping("/classes")
    public ResponseEntity<?> apiCreateClass(@RequestBody ClassReq req) {
        if (req == null || req.classId() == null || req.classId().isBlank())
            return ResponseEntity.badRequest().body("classId is required");
        if (req.courseId() == null || req.courseId().isBlank())
            return ResponseEntity.badRequest().body("courseId is required");

        int max = (req.max() == null) ? 50 : req.max();
        boolean ok = createClass(req.classId(), req.courseId(), req.teacher(), req.schedule(), max);
        if (!ok) return ResponseEntity.badRequest().body("courseId invalid");
        return ResponseEntity.ok(courseClasses.stream().filter(c -> c.getClassId().equals(req.classId())).findFirst().orElse(null));
    }

    @PostMapping("/assign")
    public ResponseEntity<?> apiAssign(@RequestParam String studentId, @RequestParam String classId) {
        boolean ok = assignStudentToClass(studentId, classId);
        return ok ? ResponseEntity.ok(courseClasses.stream().filter(c -> c.getClassId().equals(classId)).findFirst().orElse(null))
                  : ResponseEntity.badRequest().body("invalid studentId/classId or cannot add (duplicate/full?)");
    }

    @GetMapping("/report")
    public List<String> apiReport() {
        return reportClasses();
    }

    @GetMapping("/students/search")
    public List<Student> apiSearchStudent(@RequestParam("q") String q) {
        if (q == null) q = "";
        return searchStudentByName(q);
    }

    @GetMapping("/students")
    public List<Student> apiStudents() { return getStudents(); }

    @GetMapping("/courses")
    public List<Course> apiCourses() { return getCourses(); }

    @GetMapping("/classes")
    public List<CourseClass> apiClasses() { return getCourseClasses(); }
}
