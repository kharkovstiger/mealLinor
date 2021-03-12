package com.tiger.mealLinor.service;

import com.tiger.mealLinor.model.Characteristics;
import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.model.CourseType;
import com.tiger.mealLinor.model.Meal;
import com.tiger.mealLinor.repository.CourseRepository;
import com.tiger.mealLinor.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultMealService implements MealService{

    private final MealRepository mealRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public DefaultMealService(MealRepository mealRepository, CourseRepository courseRepository) {
        this.mealRepository = mealRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void calculateAllMeals() {
        List<Course> breakfasts = courseRepository.findCoursesByType(CourseType.BREAKFAST);
        List<Course> snacks = courseRepository.findCoursesByType(CourseType.SNACK);
        List<Course> dinners = courseRepository.findCoursesByType(CourseType.DINNER);
        List<Course> snacks2 = courseRepository.findCoursesByType(CourseType.SNACK_2);
        List<Course> suppers = courseRepository.findCoursesByType(CourseType.SUPPER);
        breakfasts.forEach(b -> snacks.forEach(s -> dinners.forEach(d -> snacks2.forEach(s2 -> suppers.forEach(sup -> {
            Meal meal = new Meal(b, s, d, s2, sup);
            mealRepository.saveMeal(meal);
        })))));
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public List<Meal> getMeals(Characteristics characteristics) {
        List<Meal> meals = new ArrayList<>();
        double c = 0;
        while (meals.size() < 5) {
            c += 0.01;
            meals = mealRepository.find(characteristics.getCalories(), characteristics.getProtein(), characteristics.getFats(),
                    characteristics.getCarbs(), 0.05 + c);
        }
        System.out.println(c);
        return meals;
    }
}
