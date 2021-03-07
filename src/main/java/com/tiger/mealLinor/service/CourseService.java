package com.tiger.mealLinor.service;

import com.tiger.mealLinor.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course addCourse(Course course);
}
