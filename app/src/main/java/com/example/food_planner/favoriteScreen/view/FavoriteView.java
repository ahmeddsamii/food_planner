package com.example.food_planner.favoriteScreen.view;

import androidx.lifecycle.LiveData;

import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.UserData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoriteView {
    void getAllFavMeals(List<MealDto> meals);

    void onFavoriteMealsRetrieved(List<MealDto> meals);
    void onFavoriteMealsRetrievedFailure(String errMessage);
}
