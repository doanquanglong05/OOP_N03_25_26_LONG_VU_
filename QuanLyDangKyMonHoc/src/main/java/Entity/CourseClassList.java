/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "courseClasses")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseClassList {
    @XmlElement(name = "courseClass")
    private List<CourseClass> courseClasses = new ArrayList<>();

    public CourseClassList() {}
    public CourseClassList(List<CourseClass> courseClasses) { this.courseClasses = courseClasses; }
    public List<CourseClass> getCourseClasses() { return courseClasses; }
    public void setCourseClasses(List<CourseClass> courseClasses) { this.courseClasses = courseClasses; }
}
