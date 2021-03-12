package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Meal> find(Double calories, Double protein, Double fats, Double carbs) {
        return crudMealRepository.findByCaloriesBetweenAndProteinBetweenAndFatsBetweenAndCarbsBetween(calories*0.95, calories*1.05, protein*0.95, protein*1.05, fats*0.95, fats*1.05, carbs*0.95, carbs*1.05);
    }
}
