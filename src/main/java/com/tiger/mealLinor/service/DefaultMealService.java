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
        List<Course> additions = courseRepository.findCoursesByType(CourseType.ADDITION);
        breakfasts.forEach(b -> snacks.forEach(s -> dinners.forEach(d -> snacks2.forEach(s2 -> suppers.forEach(sup -> {
            Meal meal = new Meal(b, s, d, s2, sup);
            saveMeal(meal);
            additions.forEach(a1 -> {
                Meal meala = new Meal(b, s, d, s2, sup, a1);
                saveMeal(meala);
                additions.forEach(a2 -> {
                    Meal mealaa = new Meal(b, s, d, s2, sup, a2);
                    saveMeal(mealaa);
                    additions.forEach(a3 -> {
                        Meal mealaaa = new Meal(b, s, d, s2, sup, a3);
                        saveMeal(mealaaa);
                        additions.forEach(a4 -> {
                            Meal mealaaaa = new Meal(b, s, d, s2, sup, a4);
                            saveMeal(mealaaaa);
                            additions.forEach(a5 -> {
                                Meal mealaaaaa = new Meal(b, s, d, s2, sup, a5);
                                saveMeal(mealaaaaa);
                            });
                        });
                    });
                });
            });
        })))));
    }

    private void saveMeal(Meal meal) {
        Meal existed = mealRepository.findOne(meal);
        if (existed == null) {
            mealRepository.saveMeal(meal);
        }
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

    @Override
    public boolean test() {
        List<Course> breakfasts = courseRepository.findCoursesByType(CourseType.BREAKFAST);
        List<Course> snacks = courseRepository.findCoursesByType(CourseType.SNACK);
        List<Course> dinners = courseRepository.findCoursesByType(CourseType.DINNER);
        List<Course> snacks2 = courseRepository.findCoursesByType(CourseType.SNACK_2);
        List<Course> suppers = courseRepository.findCoursesByType(CourseType.SUPPER);
        List<Course> additions = courseRepository.findCoursesByType(CourseType.ADDITION);
        Meal meal = new Meal(breakfasts.get(0), snacks.get(0), dinners.get(0), snacks2.get(0), suppers.get(0));
        mealRepository.findOne(meal);
        return false;
    }
}
