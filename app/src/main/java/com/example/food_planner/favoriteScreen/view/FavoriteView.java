package com.example.food_planner.favoriteScreen.view;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public interface FavoriteView {
    void getAllFavMeals(List<MealDto> meals);

    void onFavoriteMealsRetrievedFromFirebaseSuccess(List<MealDto> meals);
    void onFavoriteMealsRetrievedFromFirebaseFailure(String errMessage);
}
