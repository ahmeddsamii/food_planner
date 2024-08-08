package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.AllIngredientDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAllIngredients {
    @SerializedName("meals")
    private List<AllIngredientDto> AllIngredients;

    public ResponseAllIngredients() {
    }

    public ResponseAllIngredients(List<AllIngredientDto> allIngredients) {
        AllIngredients = allIngredients;
    }

    public List<AllIngredientDto> getAllIngredients() {
        return AllIngredients;
    }

    public void setAllIngredients(List<AllIngredientDto> allIngredients) {
        AllIngredients = allIngredients;
    }
}
