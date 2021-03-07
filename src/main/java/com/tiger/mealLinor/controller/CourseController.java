package com.tiger.mealLinor.controller;

import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = CourseController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
    static final String REST_URL = "/api/course";

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllCourses() {
        return new ResponseEntity(courseService.getAllCourses(), HttpStatus.OK);
    }

    @PutMapping(value = "/add")
    public ResponseEntity addCourse(@RequestBody Course course) {
        return new ResponseEntity(courseService.addCourse(course), HttpStatus.OK);
    }
}
