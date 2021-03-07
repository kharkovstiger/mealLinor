package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.model.CourseType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudCourseRepository extends MongoRepository<Course, String> {

    @Query(value = "{'type':?0}")
    List<Course> findByType(CourseType type);
}
