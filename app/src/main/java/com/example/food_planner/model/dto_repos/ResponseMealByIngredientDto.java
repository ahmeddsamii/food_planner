package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.MealByIngredientDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMealByIngredientDto {
    @SerializedName("meals")
    private List<MealByIngredientDto> meals;

    public ResponseMealByIngredientDto() {
    }

    public ResponseMealByIngredientDto(List<MealByIngredientDto> meals) {
        this.meals = meals;
    }

    public List<MealByIngredientDto> getMeals() {
        return meals;
    }

    public void setMeals(List<MealByIngredientDto> meals) {
        this.meals = meals;
    }
}
