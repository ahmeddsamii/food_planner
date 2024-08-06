package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public class ResponseRandomMeal {
    List<MealDto> meals;

    public ResponseRandomMeal() {
    }

    public ResponseRandomMeal(List<MealDto> meals) {
        this.meals = meals;
    }

    public List<MealDto> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }
}
