package com.example.food_planner.favoriteScreen.view;

import androidx.lifecycle.LiveData;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoriteView {
    void getAllFavMeals(List<MealDto> meals);
}
