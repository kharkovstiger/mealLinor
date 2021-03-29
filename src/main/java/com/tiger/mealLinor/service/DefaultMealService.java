package com.tiger.mealLinor.service;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.tiger.mealLinor.model.Characteristics;
import com.tiger.mealLinor.model.Course;
import com.tiger.mealLinor.model.CourseType;
import com.tiger.mealLinor.model.Meal;
import com.tiger.mealLinor.repository.CourseRepository;
import com.tiger.mealLinor.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultMealService implements MealService{

    private final MealRepository mealRepository;
    private final CourseRepository courseRepository;
    private final int CALORIES_MIN = 1337;
    private final int CALORIES_MAX = 1885;
    private final int CARBS_MIN = 34;
    private final int CARBS_MAX = 212;
    private final int PROTEIN_MIN = 123;
    private final int PROTEIN_MAX = 173;
    private final int FATS_MIN = 38;
    private final int FATS_MAX = 86;

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
//            Meal meal = new Meal(b, s, d, s2, sup);
//            saveMeal(meal);
            additions.forEach(a1 -> {
//                Meal meala = new Meal(b, s, d, s2, sup, a1);
//                saveMeal(meala);
                additions.forEach(a2 -> {
                    Meal mealaa = new Meal(b, s, d, s2, sup, a1, a2);
                    saveMeal(mealaa);
//                    additions.forEach(a3 -> {
//                        Meal mealaaa = new Meal(b, s, d, s2, sup, a1, a2, a3);
//                        saveMeal(mealaaa);
//                        additions.forEach(a4 -> {
//                            Meal mealaaaa = new Meal(b, s, d, s2, sup, a1, a2, a3, a4);
//                            saveMeal(mealaaaa);
//                            additions.forEach(a5 -> {
//                                Meal mealaaaaa = new Meal(b, s, d, s2, sup, a1, a2, a3, a4, a5);
//                                saveMeal(mealaaaaa);
//                            });
//                        });
//                    });
                });
            });
        })))));
    }

    private void saveMeal(Meal meal) {
//        List<Meal> existed = mealRepository.findByMainCourses(meal);
//        Meal existed = mealRepository.findOne(meal);
//        if (CollectionUtils.isEmpty(existed) || !sameAdditions(existed, meal)) {
//        if (existed == null || !sameAdditions(existed, meal))
        if (acceptedMeal(meal)) {
            mealRepository.saveMeal(meal);
        }
    }

    private boolean acceptedMeal(Meal meal) {
        return meal.getCalories() >= CALORIES_MIN && meal.getCalories() <= CALORIES_MAX
                && meal.getCarbs() >= CARBS_MIN && meal.getCarbs() <= CARBS_MAX
                && meal.getProtein() >= PROTEIN_MIN && meal.getProtein() <= PROTEIN_MAX
                && meal.getFats() >= FATS_MIN && meal.getFats() <= FATS_MAX;
    }

    private boolean sameAdditions(List<Meal> existed, Meal meal) {
        boolean result = false;
        for (Meal temp: existed) {
            if (sameAdditions(temp, meal)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean sameAdditions(Meal existed, Meal meal) {
        if (CollectionUtils.isEmpty(existed.getAdditions()) && CollectionUtils.isEmpty(meal.getAdditions())) {
            return true;
        }
        if ((CollectionUtils.isEmpty(existed.getAdditions()) && !CollectionUtils.isEmpty(meal.getAdditions()))
                || (!CollectionUtils.isEmpty(existed.getAdditions()) && CollectionUtils.isEmpty(meal.getAdditions()))) {
            return false;
        }
        if (existed.getAdditions().size() != meal.getAdditions().size()) {
            return false;
        }
        return existed.getAdditions().containsAll(meal.getAdditions());
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
                    characteristics.getCarbs(), 0.04 + c);
        }
        System.out.println(c);
        return meals;
    }
}
