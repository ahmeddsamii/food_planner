package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;

public interface MealsByCountryNetworkCallBack {
    void onMealsByCountrySuccess(ResponseMealInfoDto responseMealInfoDto);
    void onMealsByCountryFailure(String errMessage);
}
