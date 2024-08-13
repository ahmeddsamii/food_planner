package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface MealsByCategoriesNetworkCallBack {
    void onMealsByCategorySuccess(ResponseMeals responseMealInfoDto);
    void onMealsByCategoryFailure(String errMessage);
}
