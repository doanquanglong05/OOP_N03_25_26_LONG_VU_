/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "courseClass")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseClass {
    private String classId;
    private Course course;
    private String teacherName;
    private String schedule;
    private int maxStudents;

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    private List<Student> students = new ArrayList<>();

    public CourseClass() {}

    public CourseClass(String classId, Course course, String teacherName, String schedule, int maxStudents) {
        this.classId = classId;
        this.course = course;
        this.teacherName = teacherName;
        this.schedule = schedule;
        this.maxStudents = maxStudents;
    }

   public boolean addStudent(Student student) {
    // Kiểm tra nếu sinh viên đã có trong lớp
    for (Student s : students) {
        if (s.getId().equals(student.getId())) {
            return false; // Đã đăng ký rồi → không thêm nữa
        }
    }

    // Kiểm tra nếu lớp đã đầy
    if (students.size() >= maxStudents) {
        return false; // Vượt quá số lượng cho phép
    }

    return students.add(student); // Thêm mới
}


    public String getClassId() { return classId; }
    public Course getCourse() { return course; }
    public String getTeacherName() { return teacherName; }
    public String getSchedule() { return schedule; }
    public int getMaxStudents() { return maxStudents; }
    public List<Student> getStudents() { return students; }

    public void setClassId(String classId) { this.classId = classId; }
    public void setCourse(Course course) { this.course = course; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    public void setStudents(List<Student> students) { this.students = students; }
}
