/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "courses")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseList {
    @XmlElement(name = "course")
    private List<Course> courses = new ArrayList<>();

    public CourseList() {}
    public CourseList(List<Course> courses) { this.courses = courses; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}

