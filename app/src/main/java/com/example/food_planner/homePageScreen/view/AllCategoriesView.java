package com.example.food_planner.homePageScreen.view;

import com.example.food_planner.model.dto_repos.ResponseCategory;

public interface AllCategoriesView {
    void onAllCategoriesSuccess(ResponseCategory category);
    void onAllCategoriesFailure(String errMessage);
}
