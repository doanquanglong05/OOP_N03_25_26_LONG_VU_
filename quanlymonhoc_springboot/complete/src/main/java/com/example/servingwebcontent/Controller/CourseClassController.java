package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Entity.Course;
import com.example.servingwebcontent.Entity.CourseClass;
import com.example.servingwebcontent.Entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classes")
public class CourseClassController {

    // Dùng CopyOnWriteArrayList để thread-safe đơn giản
    private final List<CourseClass> courseClasses = new CopyOnWriteArrayList<>();

    private final CourseController courseController;
    private final StudentController studentController;

    public CourseClassController(CourseController courseController, StudentController studentController) {
        this.courseController = courseController;
        this.studentController = studentController;
    }

    /* ======================= DTOs ======================= */
    public record CreateClassRequest(
            String classId,
            String courseId,
            String teacherName,
            String schedule,
            Integer maxStudents
    ) {}

    // Thêm classId để cho phép đổi mã lớp khi cập nhật
    public record UpdateClassRequest(
            String classId,          // optional: đổi mã lớp
            String courseId,         // optional: đổi sang môn khác
            String teacherName,      // optional
            String schedule,         // optional
            Integer maxStudents      // optional
    ) {}

    /* =================== Logic nội bộ =================== */

    public boolean addCourseClass(String classId, String courseId, String teacher, String schedule, int maxStudents) {
        if (classId == null || classId.isBlank()) return false;
        String cls = classId.trim();
        if (findByClassId(cls) != null) return false;

        if (courseId == null || courseId.isBlank()) return false;
        Course course = courseController.findCourseById(courseId.trim());
        if (course == null) return false;

        CourseClass newClass = new CourseClass(cls, course,
                teacher == null ? null : teacher.trim(),
                schedule == null ? null : schedule.trim(),
                maxStudents);
        courseClasses.add(newClass);
        return true;
    }

    public boolean deleteCourseClass(String classId) {
        return courseClasses.removeIf(c -> c.getClassId().equals(classId));
    }

    public boolean assignStudentToClass(String studentId, String classId) {
        CourseClass courseClass = findByClassId(classId);
        Student student = studentController.findStudentById(studentId);
        if (courseClass != null && student != null) {
            return courseClass.addStudent(student);
        }
        return false;
    }

    public boolean removeStudentFromClass(String studentId, String classId) {
        CourseClass courseClass = findByClassId(classId);
        if (courseClass != null) {
            return courseClass.getStudents().removeIf(s -> s.getId().equals(studentId));
        }
        return false;
    }

    public CourseClass findByClassId(String classId) {
        if (classId == null) return null;
        String key = classId.trim();
        return courseClasses.stream()
                .filter(c -> c.getClassId().equals(key))
                .findFirst()
                .orElse(null);
    }

    public List<CourseClass> findByCourseId(String courseId) {
        String key = courseId == null ? "" : courseId.trim();
        return courseClasses.stream()
                .filter(c -> c.getCourse() != null && c.getCourse().getCourseId().equals(key))
                .collect(Collectors.toList());
    }

    public List<CourseClass> getAllClasses() {
        return courseClasses;
    }

    public List<CourseClass> getRegisteredClassesByStudentId(String studentId) {
        return courseClasses.stream()
                .filter(c -> c.getStudents() != null &&
                        c.getStudents().stream().anyMatch(s -> s.getId().equals(studentId)))
                .collect(Collectors.toList());
    }

    /* ===================== REST API ===================== */

    @GetMapping
    public List<CourseClass> apiGetAll() {
        return getAllClasses();
    }

    @GetMapping("/{classId}")
    public ResponseEntity<?> apiGetOne(@PathVariable String classId) {
        CourseClass cc = findByClassId(classId);
        return (cc == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cc);
    }

    @PostMapping
    public ResponseEntity<?> apiCreate(@RequestBody CreateClassRequest req) {
        if (req.classId() == null || req.classId().isBlank())
            return ResponseEntity.badRequest().body("classId is required");
        if (findByClassId(req.classId()) != null)
            return ResponseEntity.badRequest().body("classId already exists");
        if (req.courseId() == null || req.courseId().isBlank())
            return ResponseEntity.badRequest().body("courseId is required");

        int max = (req.maxStudents() == null) ? 50 : req.maxStudents();
        boolean ok = addCourseClass(req.classId(), req.courseId(), req.teacherName(), req.schedule(), max);
        return ok ? ResponseEntity.ok(findByClassId(req.classId().trim()))
                  : ResponseEntity.badRequest().body("Invalid courseId or cannot create");
    }

    @PutMapping("/{classId}")
    public ResponseEntity<?> apiUpdate(@PathVariable String classId, @RequestBody UpdateClassRequest req) {
        CourseClass current = findByClassId(classId);
        if (current == null) return ResponseEntity.notFound().build();

        // 1) Đổi mã lớp nếu có classId mới
        if (req.classId() != null && !req.classId().isBlank()) {
            String newId = req.classId().trim();
            if (!newId.equals(current.getClassId())) {
                if (findByClassId(newId) != null) {
                    return ResponseEntity.badRequest().body("classId already exists: " + newId);
                }
                current.setClassId(newId);
            }
        }

        // 2) Đổi môn nếu có courseId mới
        if (req.courseId() != null && !req.courseId().isBlank()) {
            Course c = courseController.findCourseById(req.courseId().trim());
            if (c == null) return ResponseEntity.badRequest().body("courseId invalid");
            current.setCourse(c);
        }

        // 3) Các trường khác
        if (req.teacherName() != null) current.setTeacherName(req.teacherName().trim());
        if (req.schedule() != null)    current.setSchedule(req.schedule().trim());
        if (req.maxStudents() != null) current.setMaxStudents(req.maxStudents());

        // Không cần replace trong list: current là tham chiếu trong courseClasses
        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<?> apiDelete(@PathVariable String classId) {
        return deleteCourseClass(classId) ? ResponseEntity.noContent().build()
                                          : ResponseEntity.notFound().build();
    }

    @PostMapping("/{classId}/assign")
    public ResponseEntity<?> apiAssign(@PathVariable String classId, @RequestParam String studentId) {
        boolean ok = assignStudentToClass(studentId, classId);
        return ok ? ResponseEntity.ok(findByClassId(classId))
                  : ResponseEntity.badRequest().body("studentId/classId invalid or cannot add (duplicate/full?)");
    }

    @PostMapping("/{classId}/remove")
    public ResponseEntity<?> apiRemove(@PathVariable String classId, @RequestParam String studentId) {
        boolean ok = removeStudentFromClass(studentId, classId);
        return ok ? ResponseEntity.ok(findByClassId(classId))
                  : ResponseEntity.badRequest().body("student not in class or classId invalid");
    }

    @GetMapping("/by-course/{courseId}")
    public List<CourseClass> apiByCourse(@PathVariable String courseId) {
        return findByCourseId(courseId);
    }

    @GetMapping("/registered/{studentId}")
    public List<CourseClass> apiRegisteredByStudent(@PathVariable String studentId) {
        return getRegisteredClassesByStudentId(studentId);
    }
}
