package com.tiger.mealLinor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Course {
    @Id
    private String Id;
    private String name;
    private Integer calories;
    private Integer fats;
    private Integer protein;
    private Integer carbs;
    private CourseType type;
}
