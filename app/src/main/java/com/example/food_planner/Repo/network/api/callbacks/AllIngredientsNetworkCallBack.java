package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseAllIngredients;

public interface AllIngredientsNetworkCallBack {
    void onAllIngredientSuccess(ResponseAllIngredients allIngredients);
    void onAllIngredientsFailure(String errMessage);
}
