package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultMealRepository implements MealRepository{

    private final CrudMealRepository crudMealRepository;

    @Autowired
    public DefaultMealRepository(CrudMealRepository crudMealRepository) {
        this.crudMealRepository = crudMealRepository;
    }

    @Override
    public void saveMeal(Meal meal) {
        crudMealRepository.save(meal);
    }

    @Override
    public List<Meal> findAll() {
        return crudMealRepository.findAll();
    }

    @Override
    public List<Meal> find(Double calories, Double protein, Double fats, Double carbs, double percentage) {
        return crudMealRepository.findByCaloriesBetweenAndProteinBetweenAndFatsBetweenAndCarbsBetween(calories*(1-percentage),
                calories*(1+percentage), protein*(1-percentage), protein*(1+percentage),
                fats*(1-percentage), fats*(1+percentage), carbs*(1-percentage), carbs*(1+percentage));
    }

    @Override
    public List<Meal> findAll(Meal meal) {
        Example<Meal> example = Example.of(meal);
        return crudMealRepository.findAll(example);
    }

    @Override
    public Meal findOne(Meal meal) {
        Example<Meal> example = Example.of(meal);
        return crudMealRepository.findOne(example).orElse(null);
    }

    @Override
    public List<Meal> findByMainCourses(Meal meal) {
//        return crudMealRepository.findByMainCourses(meal.getBreakfast().getId(), meal.getSnack().getId(), meal.getDinner().getId(),
//                meal.getSnack2().getId(), meal.getSupper().getId());
        return crudMealRepository.findByBreakfastAndSnackAndDinnerAndSnack2AndSupper(meal.getBreakfast(), meal.getSnack(),
                meal.getDinner(), meal.getSnack2(), meal.getSupper());
    }
}
