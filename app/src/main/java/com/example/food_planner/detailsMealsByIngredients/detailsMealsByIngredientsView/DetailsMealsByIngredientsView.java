package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView;

import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dtos.MealDto;

public interface DetailsMealsByIngredientsView {
    void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto responseMealByIngredientDto);
    void onAllMealsByIngredientsFailure(String errMessage);

    void onItemByNameSuccess(MealDto mealDto);
    void onItemByNameFailure(String errMessage);
}
