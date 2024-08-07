package com.example.food_planner.searchScreen.view;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public interface MealsSearchByNameView {
    void onMealSearchByNameSuccess(List<MealDto> mealDto);
    void onMealSearchByNameFailure(String message);
}
