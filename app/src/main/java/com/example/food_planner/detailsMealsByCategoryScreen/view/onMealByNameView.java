package com.example.food_planner.detailsMealsByCategoryScreen.view;

import com.example.food_planner.model.dtos.MealDto;

public interface onMealByNameView {
   void onMealByNameSuccess(MealDto mealDto);
   void onMealByNameFailure(String errMessage);
}
