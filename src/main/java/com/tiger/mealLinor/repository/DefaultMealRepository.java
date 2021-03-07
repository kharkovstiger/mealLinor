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
}
