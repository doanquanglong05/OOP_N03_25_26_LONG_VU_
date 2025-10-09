package com.quanly.quanly_dangky.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseClassList {
    private List<CourseClass> courseclasses = new ArrayList<>();

    public CourseClassList() {}

    public CourseClassList(List<CourseClass> courseclasses) {
        this.courseclasses = courseclasses;
    }

    public List<CourseClass> getCourseclasses() { return courseclasses; }
    public void setCourseclasses(List<CourseClass> courseclasses) { this.courseclasses = courseclasses; }
}
