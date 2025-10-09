/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Entity.*;
import Utils.*;
import java.util.*;
import java.util.stream.Collectors;

public class CourseClassController {
    private List<CourseClass> courseClasses = new ArrayList<>();
    private final String FILE_NAME = "classes.xml";

    private final CourseController courseController;
    private final StudentController studentController;

    public CourseClassController(CourseController courseController, StudentController studentController) {
        this.courseController = courseController;
        this.studentController = studentController;

        FileUtils.initFileIfNotExists(FILE_NAME, new CourseClassList());
        CourseClassList list = (CourseClassList) FileUtils.readXMLFile(FILE_NAME, CourseClassList.class);
        if (list != null) {
            courseClasses = list.getCourseClasses();
        }
    }

    // Thêm lớp học phần mới
    public boolean addCourseClass(String classId, String courseId, String teacher, String schedule, int maxStudents) {
        if (findByClassId(classId) != null) return false;

        Course course = courseController.findCourseById(courseId);
        if (course == null) return false;

        CourseClass newClass = new CourseClass(classId, course, teacher, schedule, maxStudents);
        courseClasses.add(newClass);
        saveToFile();
        return true;
    }

    // Cập nhật thông tin lớp học phần
    public boolean updateCourseClass(CourseClass updatedClass) {
        for (int i = 0; i < courseClasses.size(); i++) {
            if (courseClasses.get(i).getClassId().equals(updatedClass.getClassId())) {
                courseClasses.set(i, updatedClass);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Xóa lớp học phần
    public boolean deleteCourseClass(String classId) {
        boolean removed = courseClasses.removeIf(c -> c.getClassId().equals(classId));
        if (removed) saveToFile();
        return removed;
    }

    // Gán sinh viên vào lớp
    public boolean assignStudentToClass(String studentId, String classId) {
        CourseClass courseClass = findByClassId(classId);
        Student student = studentController.findStudentById(studentId);

        if (courseClass != null && student != null) {
            boolean added = courseClass.addStudent(student);
            if (added) {
                saveToFile();
            }
            return added;
        }
        return false;
    }
public boolean removeStudentFromClass(String studentId, String classId) {
    CourseClass courseClass = findByClassId(classId);
    Student student = studentController.findStudentById(studentId);

    if (courseClass != null && student != null) {
        boolean removed = courseClass.getStudents().removeIf(s -> s.getId().equals(studentId));
        if (removed) {
            saveToFile();
        }
        return removed;
    }
    return false;
}

    // Tìm lớp theo mã
    public CourseClass findByClassId(String classId) {
        return courseClasses.stream()
                .filter(c -> c.getClassId().equals(classId))
                .findFirst()
                .orElse(null);
    }

    // Tìm tất cả lớp của 1 học phần
    public List<CourseClass> findByCourseId(String courseId) {
        return courseClasses.stream()
                .filter(c -> c.getCourse().getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    public List<CourseClass> getAllClasses() {
        return courseClasses;
    }

    private void saveToFile() {
        FileUtils.writeXMLtoFile(FILE_NAME, new CourseClassList(courseClasses));
    }
    public List<CourseClass> getRegisteredClassesByStudentId(String studentId) {
    return courseClasses.stream()
        .filter(c -> c.getStudents().stream()
                     .anyMatch(s -> s.getId().equals(studentId)))
        .collect(Collectors.toList());
}

}
