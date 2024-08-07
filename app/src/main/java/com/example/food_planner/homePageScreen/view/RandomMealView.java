package com.example.food_planner.homePageScreen.view;

import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface RandomMealView {
    void onRandomMealSuccess(ResponseMeals randomMeal);
    void onRandomMealFailure(String errMessage);
}
