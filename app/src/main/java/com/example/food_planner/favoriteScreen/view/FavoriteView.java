package com.example.food_planner.favoriteScreen.view;

import androidx.lifecycle.LiveData;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public interface FavoriteView {
    void getAllFavMeals(LiveData<List<MealDto>> meals);
}
