package com.example.servingwebcontent.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.Entity.Student;
import com.example.servingwebcontent.Utils.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @Autowired
    private FileDbService fileDb;

    // Load dữ liệu khi khởi động
    public StudentController(FileDbService fileDb) {
        this.fileDb = fileDb;
        this.students = fileDb.loadStudents();
    }

    /* ================== HÀM NỘI BỘ ================== */

    public Student findStudentById(String id) {
        if (id == null) return null;
        String key = id.trim();
        return students.stream().filter(s -> key.equals(s.getId())).findFirst().orElse(null);
    }

    public boolean addStudent(Student student) {
        if (student == null || student.getId() == null) return false;
        String id = student.getId().trim();
        if (id.isEmpty()) return false;
        if (findStudentById(id) != null) return false;
        student.setId(id);
        students.add(student);
        fileDb.saveStudents(students);
        return true;
    }

    public boolean deleteStudent(String id) {
        boolean ok = students.removeIf(s -> s.getId().equals(id.trim()));
        if (ok) fileDb.saveStudents(students);
        return ok;
    }

    public List<Student> searchByName(String keyword) {
        String k = (keyword == null) ? "" : keyword.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName() != null && s.getName().toLowerCase().contains(k)) {
                result.add(s);
            }
        }
        return result;
    }

    /* ================== REST API ================== */

    @GetMapping
    public List<Student> apiList() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> apiGet(@PathVariable String id) {
        Student s = findStudentById(id);
        return (s == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<?> apiCreate(@RequestBody Student student) {
        if (student == null || student.getId() == null || student.getId().isBlank())
            return ResponseEntity.badRequest().body("Student ID is required");
        if (student.getDateOfBirth() == null)
            return ResponseEntity.badRequest().body("dateOfBirth is required (yyyy-MM-dd)");
        if (!addStudent(student))
            return ResponseEntity.badRequest().body("Student ID already exists");
        return ResponseEntity.ok(student);
    }

    /**
     * Cho phép sửa thông tin và ĐỔI MSV:
     * - Gọi theo /api/students/{oldId}
     * - Body có thể chứa id (newId) để đổi MSV
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> apiUpdate(@PathVariable String id, @RequestBody Student updated) {
        if (id == null || id.isBlank()) return ResponseEntity.badRequest().body("Old ID is required");
        Student current = findStudentById(id);
        if (current == null) return ResponseEntity.notFound().build();

        // --- Đổi MSV nếu body có id mới ---
        if (updated.getId() != null) {
            String newId = updated.getId().trim();
            if (newId.isEmpty()) {
                return ResponseEntity.badRequest().body("New Student ID cannot be empty");
            }
            if (!newId.equals(current.getId())) {
                if (findStudentById(newId) != null) {
                    return ResponseEntity.badRequest().body("Student ID already exists: " + newId);
                }
                current.setId(newId);
            }
        }

        // --- Cập nhật các field khác (nếu có) ---
        if (updated.getName() != null)        current.setName(updated.getName().trim());
        if (updated.getGender() != null)      current.setGender(updated.getGender().trim());
        if (updated.getDateOfBirth() != null) current.setDateOfBirth(updated.getDateOfBirth());
        if (updated.getEmail() != null)       current.setEmail(updated.getEmail().trim());

        // current là tham chiếu trong list -> chỉ cần save
        fileDb.saveStudents(students);
        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apiDelete(@PathVariable String id) {
        boolean ok = deleteStudent(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Student> apiSearch(@RequestParam(name = "q", required = false) String q) {
        return searchByName(q);
    }
}
