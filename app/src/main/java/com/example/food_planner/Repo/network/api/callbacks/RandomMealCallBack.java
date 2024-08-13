package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface RandomMealCallBack {
    void onRandomMealSuccess(ResponseMeals randomMeal);
    void onRandomMealFailure(String errMessage);
}
