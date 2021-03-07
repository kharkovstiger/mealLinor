package com.tiger.mealLinor.repository;

import com.tiger.mealLinor.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends MongoRepository<Meal, String> {

    @Override
    @Transactional
    Meal save(Meal meal);
}
