package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dtos.MealDto;

public interface ItemByNameNetworkCallBack {
    void onItemByNameSuccess(MealDto mealDto);
    void onItemByNameFailure(String errMessage);
}
