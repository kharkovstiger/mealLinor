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
    @DBRef
    private List<Course> additions;
    private Double calories;
    private Double fats;
    private Double protein;
    private Double carbs;

    public Meal(Course breakfast, Course snack, Course dinner, Course snack2, Course supper, Course... additions) {
        this.breakfast = breakfast;
        this.snack = snack;
        this.dinner = dinner;
        this.snack2 = snack2;
        this.supper = supper;
        List<Course> courses = Arrays.asList(breakfast, snack, dinner, snack2, supper);
        this.calories = courses.stream().mapToDouble(Course::getCalories).sum();
        this.fats = courses.stream().mapToDouble(Course::getFats).sum();
        this.protein = courses.stream().mapToDouble(Course::getProtein).sum();
        this.carbs = courses.stream().mapToDouble(Course::getCarbs).sum();
        if (additions.length > 0) {
            this.additions = Arrays.asList(additions);
        }
    }
}
