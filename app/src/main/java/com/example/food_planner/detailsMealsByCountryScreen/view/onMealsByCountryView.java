package com.example.food_planner.detailsMealsByCountryScreen.view;

import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dtos.MealDto;

public interface onMealsByCountryView {
    public void onSuccess(ResponseMealInfoDto responseMealInfoDto);
    public void onFailure(String errMessage);
    void onItemByNameSuccess(MealDto mealDto);
}
