package com.example.food_planner.homePageScreen.view;

import com.example.food_planner.model.dto_repos.ResponseRandomMeal;

public interface RandomMealView {
    void onRandomMealSuccess(ResponseRandomMeal randomMeal);
    void onRandomMealFailure(String errMessage);
}
