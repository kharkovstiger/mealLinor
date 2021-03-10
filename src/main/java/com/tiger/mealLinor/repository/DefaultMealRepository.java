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
        return crudMealRepository.findByCaloriesGreaterThanEqualAndCaloriesLessThanEqualAndProteinGreaterThanEqualAndProteinLessThanEqualAndFatsGreaterThanEqualAndFatsLessThanEqualAndCarbsGreaterThanEqualAndCarbsLessThanEqual(calories*0.9, calories*1.1, protein*0.9, protein*1.1, fats*0.9, fats*1.1, carbs*0.9, carbs*1.1);
    }
}
