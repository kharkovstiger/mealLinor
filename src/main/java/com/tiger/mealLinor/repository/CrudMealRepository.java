package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends MongoRepository<Meal, String> {

    @Override
    @Transactional
    Meal save(Meal meal);

    @Query(value = "{}")
    List<Meal> find(Double calories, Double protein, Double fats, Double carbs);

    List<Meal> findByCaloriesGreaterThanAndLessThanAndProteinGreaterThanAndLessThanAndFatsGreaterThanAndLessThanAndCarbsGreaterThanAndLessThan(Double caloriesMin, Double caloriesMax, Double proteinMin, Double proteinMax, Double fatsMin, Double fatsMax, Double carbsMin, Double carbsMax);
}
