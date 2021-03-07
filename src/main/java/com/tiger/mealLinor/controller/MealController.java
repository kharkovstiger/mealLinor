package com.tiger.mealLinor.controller;

import com.tiger.mealLinor.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {
    static final String REST_URL = "/api/meal";

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(value = "/calculate")
    public ResponseEntity calculateAllMeals() {
        mealService.calculateAllMeals();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllMeals() {
        return new ResponseEntity(mealService.getAllMeals(), HttpStatus.OK);
    }

    
}
