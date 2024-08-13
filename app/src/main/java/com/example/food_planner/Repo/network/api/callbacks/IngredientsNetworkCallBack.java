package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface IngredientsNetworkCallBack {
    void onIngredientSuccess(ResponseMeals ingredients);
    void onIngredientFailure(String errMessage);
}
