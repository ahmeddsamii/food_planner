package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.IngredientDto;
import com.example.food_planner.model.dtos.MealDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseIngredient {
    @SerializedName("meals")
    List<IngredientDto> ingredients;

    public ResponseIngredient() {
    }

    public ResponseIngredient(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
