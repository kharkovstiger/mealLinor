package com.tiger.mealLinor.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@Document
@RequiredArgsConstructor
public class Meal {

    @DBRef
    private Course breakfast;
    @DBRef
    private Course snack;
    @DBRef
    private Course dinner;
    @DBRef
    private Course snack2;
    @DBRef
    private Course supper;
    private Integer calories;
    private Integer fats;
    private Integer protein;
    private Integer carbs;

    public Meal(Course breakfast, Course snack, Course dinner, Course snack2, Course supper) {
        this.breakfast = breakfast;
        this.snack = snack;
        this.dinner = dinner;
        this.snack2 = snack2;
        this.supper = supper;
        List<Course> courses = Arrays.asList(breakfast, snack, dinner, snack2, supper);
        this.calories = courses.stream().mapToInt(Course::getCalories).sum();
        this.fats = courses.stream().mapToInt(Course::getFats).sum();
        this.protein = courses.stream().mapToInt(Course::getProtein).sum();
        this.carbs = courses.stream().mapToInt(Course::getCarbs).sum();
    }
}
