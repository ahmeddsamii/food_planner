package com.example.food_planner.detailsMealScreen.view;

import com.example.food_planner.model.dto_repos.ResponseIngredient;

public interface AllIngredientsView {

    void onAllIngredientsSuccess(ResponseIngredient ingredients);
    void onAllIngredientsFailure(String errMessage);
}
