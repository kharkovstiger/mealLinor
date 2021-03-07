package com.tiger.mealLinor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MealLinorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealLinorApplication.class, args);
    }

}
