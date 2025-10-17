/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentList {
    @XmlElement(name = "student")
    private List<Student> students = new ArrayList<>();

    public StudentList() {}
    public StudentList(List<Student> students) { this.students = students; }
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}
