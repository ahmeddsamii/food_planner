package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView;

import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;

public interface DetailsMealsByIngredientsView {
    void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto responseMealByIngredientDto);
    void onAllMealsByIngredientsFailure(String errMessage);
}
