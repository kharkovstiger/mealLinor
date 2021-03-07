package com.tiger.mealLinor.service;

import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCourseService implements CourseService{

    private final CourseRepository courseRepository;

    @Autowired
    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
