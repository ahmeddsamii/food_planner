package com.example.food_planner.favoriteScreen.view;

import com.example.food_planner.model.dtos.MealDto;

public interface onFavClickListener {
    void onFavItemClicked(MealDto mealDto);

    void onFavItemDelete(MealDto mealDto);
}
