/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Entity.*;
import Utils.*;
import java.util.ArrayList;
import java.util.List;

public class CourseController {
    private List<Course> courses = new ArrayList<>();
    private final String FILE_NAME = "courses.xml";

    public CourseController() {
        FileUtils.initFileIfNotExists(FILE_NAME, new CourseList());
        CourseList list = (CourseList) FileUtils.readXMLFile(FILE_NAME, CourseList.class);
        if (list != null) {
            courses = list.getCourses();
        }
    }

    public boolean addCourse(Course course) {
        if (findCourseById(course.getCourseId()) != null) return false;
        courses.add(course);
        saveToFile();
        return true;
    }

    public boolean updateCourse(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(updatedCourse.getCourseId())) {
                courses.set(i, updatedCourse);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id) {
        boolean removed = courses.removeIf(c -> c.getCourseId().equals(id));
        if (removed) saveToFile();
        return removed;
    }

    public Course findCourseById(String id) {
        return courses.stream().filter(c -> c.getCourseId().equals(id)).findFirst().orElse(null);
    }

    public List<Course> searchByName(String keyword) {
        keyword = keyword.toLowerCase();
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCourseName().toLowerCase().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    private void saveToFile() {
        FileUtils.writeXMLtoFile(FILE_NAME, new CourseList(courses));
    }
}
