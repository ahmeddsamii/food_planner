package com.example.food_planner.detailsMealsByCategoryScreen.view;

import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface CategoryDetailsView {
    public void onSuccess(ResponseMeals responseMealInfoDto);
    public void onFailure(String errMessage);

}
