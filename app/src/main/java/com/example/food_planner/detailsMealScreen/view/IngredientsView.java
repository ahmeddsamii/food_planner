package com.example.food_planner.detailsMealScreen.view;

import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMeals;

public interface IngredientsView {

    void onAllIngredientsSuccess(ResponseMeals ingredients);
    void onAllIngredientsFailure(String errMessage);
}
