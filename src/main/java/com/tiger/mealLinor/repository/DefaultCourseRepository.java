package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.model.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultCourseRepository implements CourseRepository {

    private final CrudCourseRepository crudCourseRepository;

    @Autowired
    public DefaultCourseRepository(CrudCourseRepository crudCourseRepository) {
        this.crudCourseRepository = crudCourseRepository;
    }

    @Override
    public List<Course> findCoursesByType(CourseType type) {
        return crudCourseRepository.findByType(type);
    }

    @Override
    public List<Course> findAll() {
        return crudCourseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        return crudCourseRepository.save(course);
    }
}
