package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Entity.Course;
import com.example.servingwebcontent.Utils.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseController (Spring Boot, in-memory)
 * - Không dùng XML / FileUtils nữa.
 * - Cho phép đổi mã môn học (courseId) khi cập nhật.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private List<Course> courses = new ArrayList<>();

    public CourseController() {
        // (tùy chọn) Seed dữ liệu mẫu:
        // courses.add(new Course("C001", "Lập trình Java", 3, "Mô tả Java"));
        // courses.add(new Course("C002", "Cơ sở dữ liệu", 3, "Mô tả CSDL"));
    }

    // ===== Các hàm cũ =====

    public boolean addCourse(Course course) {
        if (course == null || course.getCourseId() == null) return false;
        if (findCourseById(course.getCourseId()) != null) return false;
        courses.add(course);
        return true;
    }

    public boolean updateCourse(Course updatedCourse) {
        if (updatedCourse == null || updatedCourse.getCourseId() == null) return false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(updatedCourse.getCourseId())) {
                courses.set(i, updatedCourse);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id) {
        if (id == null) return false;
        return courses.removeIf(c -> id.equals(c.getCourseId()));
    }

    public Course findCourseById(String id) {
        if (id == null) return null;
        return courses.stream()
                .filter(c -> id.equals(c.getCourseId()))
                .findFirst()
                .orElse(null);
    }

    public List<Course> searchByName(String keyword) {
        if (keyword == null || keyword.isBlank()) return getAllCourses();
        keyword = keyword.toLowerCase();
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            String name = c.getCourseName() == null ? "" : c.getCourseName().toLowerCase();
            if (name.contains(keyword)) result.add(c);
        }
        return result;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    // ===== REST API =====

    @GetMapping
    public List<Course> apiList() {
        return getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> apiGet(@PathVariable String id) {
        Course c = findCourseById(id);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<?> apiCreate(@RequestBody Course course) {
        if (course == null || course.getCourseId() == null || course.getCourseId().isBlank()) {
            return ResponseEntity.badRequest().body("courseId is required");
        }
        if (!addCourse(course)) {
            return ResponseEntity.badRequest().body("courseId already exists or invalid");
        }
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> apiUpdate(@PathVariable String id, @RequestBody Course patch) {
        Course current = findCourseById(id);
        if (current == null) return ResponseEntity.notFound().build();

        // ✅ Cho phép đổi mã môn học (courseId)
        if (patch.getCourseId() != null && !patch.getCourseId().isBlank()) {
            String newId = patch.getCourseId().trim();
            if (!newId.equals(current.getCourseId())) {
                // Kiểm tra trùng mã
                if (findCourseById(newId) != null) {
                    return ResponseEntity.badRequest().body("courseId already exists: " + newId);
                }
                current.setCourseId(newId);
            }
        }

        // Cập nhật các trường còn lại
        if (patch.getCourseName() != null) current.setCourseName(patch.getCourseName());
        if (patch.getCredit() != 0) current.setCredit(patch.getCredit());
        if (patch.getDescription() != null) current.setDescription(patch.getDescription());

        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apiDelete(@PathVariable String id) {
        boolean ok = deleteCourse(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Course> apiSearch(@RequestParam(name = "q", required = false) String q) {
        return searchByName(q == null ? "" : q);
    }
}
