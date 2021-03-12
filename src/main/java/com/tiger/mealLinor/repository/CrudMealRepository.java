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

    @Query(value = "{'calories': {$gt: ?0, $lt: ?1}, 'protein': {$gt: ?2, $lt: ?3}, 'fats': {$gt: ?4, $lt: ?5}, 'carbs': {$gt: ?6, $lt: ?7}}")
    List<Meal> find(Double caloriesMin, Double caloriesMax, Double proteinMin, Double proteinMax, Double fatsMin,
                    Double fatsMax, Double carbsMin, Double carbsMax);

    List<Meal> findByCaloriesBetweenAndProteinBetweenAndFatsBetweenAndCarbsBetween(Double caloriesMin, Double caloriesMax,
                                                                                   Double proteinMin, Double proteinMax,
                                                                                   Double fatsMin, Double fatsMax,
                                                                                   Double carbsMin, Double carbsMax);
}
