package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.model.CourseType;

import java.util.List;

public interface CourseRepository {
    List<Course> findCoursesByType(CourseType type);
}
