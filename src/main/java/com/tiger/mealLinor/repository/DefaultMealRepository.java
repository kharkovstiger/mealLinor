package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Meal findOne(Meal meal) {
        Example<Meal> example = Example.of(meal);
        return crudMealRepository.findOne(example).orElse(null);
    }
}
