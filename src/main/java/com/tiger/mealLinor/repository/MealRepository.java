package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Meal;

import java.util.List;

public interface MealRepository {
    void saveMeal(Meal meal);

    List<Meal> findAll();

    List<Meal> find(Double calories, Double protein, Double fats, Double carbs, double percentage);

    List<Meal> findAll(Meal meal);

    Meal findOne(Meal meal);

    List<Meal> findByMainCourses(Meal meal);
}
