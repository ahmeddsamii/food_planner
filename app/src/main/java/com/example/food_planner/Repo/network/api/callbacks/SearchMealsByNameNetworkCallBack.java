package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface SearchMealsByNameNetworkCallBack {
    void onSearchMealsByNameSuccess(ResponseMeals responseMeals);
    void onSearchMealsByNameFailure(String errMessage);
}
