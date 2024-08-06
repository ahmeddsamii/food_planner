package com.example.food_planner.detailsMealsByCategoryScreen.view;

import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;

public interface CategoryDetailsView {
    public void onSuccess(ResponseMealInfoDto responseMealInfoDto);
    public void onFailure(String errMessage);

}
