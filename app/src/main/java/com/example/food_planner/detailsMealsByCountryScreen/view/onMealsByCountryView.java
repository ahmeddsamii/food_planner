package com.example.food_planner.detailsMealsByCountryScreen.view;

import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;

public interface onMealsByCountryView {
    public void onSuccess(ResponseMealInfoDto responseMealInfoDto);
    public void onFailure(String errMessage);
}
