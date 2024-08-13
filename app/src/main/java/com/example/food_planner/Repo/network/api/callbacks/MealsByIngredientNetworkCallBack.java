package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;

public interface MealsByIngredientNetworkCallBack {
    void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto meals);
    void onAllMealsByIngredientsFailure(String errMessage);
}
