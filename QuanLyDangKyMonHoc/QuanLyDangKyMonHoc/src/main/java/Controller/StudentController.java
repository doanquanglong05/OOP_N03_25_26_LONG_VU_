/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Entity.*;
import Utils.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.xml";

    public StudentController() {
        FileUtils.initFileIfNotExists(FILE_NAME, new StudentList());
        StudentList list = (StudentList) FileUtils.readXMLFile(FILE_NAME, StudentList.class);
        if (list != null) {
            students = list.getStudents();
        }
    }

    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()) != null) return false;
        students.add(student);
        saveToFile();
        return true;
    }

    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        boolean removed = students.removeIf(s -> s.getId().equals(id));
        if (removed) saveToFile();
        return removed;
    }

    public Student findStudentById(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        keyword = keyword.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private void saveToFile() {
        FileUtils.writeXMLtoFile(FILE_NAME, new StudentList(students));
    }
}
