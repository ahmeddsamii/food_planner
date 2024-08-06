package com.example.food_planner.Repo;

import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public interface NetworkCallBack {
    void onRandomMealSuccess(ResponseRandomMeal randomMeal);
    void onRandomMealFailure(String errMessage);

    void onAllCategoriesSuccess(ResponseCategory responseCategory);
    void onAllCategoriesFailure(String errMessage);

    void onAllIngredientsSuccess(ResponseIngredient ingredients);
    void onAllIngredientFailure(String errMessage);

    void onItemByNameSuccess(MealDto mealDto);
    void onItemByNameFailure(String errMessage);

    void onAllCountriesSuccess(ResponseCountry countries);
    void onAllCountriesFailure(String errMessage);


    void onMealsByCategorySuccess(ResponseMealInfoDto responseMealInfoDto);
    void onMealsByCategoryFailure(String errMessage);

    void onMealsByCountrySuccess(ResponseMealInfoDto responseMealInfoDto);
    void onMealsByCountryFailure(String errMessage);
}
