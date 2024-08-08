package com.example.food_planner.searchScreen.view;

import com.example.food_planner.model.dto_repos.ResponseAllIngredients;

public interface AllIngredientsSearchView {
    void onAllIngredientsSuccess(ResponseAllIngredients allIngredients);
    void onAllIngredientsFailure(String errMessage);
}
