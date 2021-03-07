package com.tiger.mealLinor.service;

import com.tiger.mealLinor.model.Meal;

import java.util.List;

public interface MealService {
    void calculateAllMeals();

    List<Meal> getAllMeals();
}
