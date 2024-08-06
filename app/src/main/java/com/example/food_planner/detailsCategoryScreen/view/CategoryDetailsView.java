package com.example.food_planner.detailsCategoryScreen.view;

import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dtos.MealDto;

public interface CategoryDetailsView {
    public void onSuccess(ResponseMealInfoDto responseMealInfoDto);
    public void onFailure(String errMessage);

}
