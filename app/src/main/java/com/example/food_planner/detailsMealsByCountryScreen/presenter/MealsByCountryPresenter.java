package com.example.food_planner.detailsMealsByCountryScreen.presenter;

import com.example.food_planner.Repo.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCountryScreen.view.onMealsByCountryView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;
import com.example.food_planner.model.dtos.MealDto;

public class MealsByCountryPresenter implements NetworkCallBack {

    Repo repo;
    onMealsByCountryView view;

    public MealsByCountryPresenter(Repo repo, onMealsByCountryView view){
        this.repo = repo;
        this.view = view;
    }

    public void getMealsByCountry(String country){
        repo.getMealsByCountry(country, this);
    }

    @Override
    public void onRandomMealSuccess(ResponseRandomMeal randomMeal) {

    }

    @Override
    public void onRandomMealFailure(String errMessage) {

    }

    @Override
    public void onAllCategoriesSuccess(ResponseCategory responseCategory) {

    }

    @Override
    public void onAllCategoriesFailure(String errMessage) {

    }

    @Override
    public void onAllIngredientsSuccess(ResponseIngredient ingredients) {

    }

    @Override
    public void onAllIngredientFailure(String errMessage) {

    }

    @Override
    public void onItemByNameSuccess(MealDto mealDto) {

    }

    @Override
    public void onItemByNameFailure(String errMessage) {

    }

    @Override
    public void onAllCountriesSuccess(ResponseCountry countries) {

    }

    @Override
    public void onAllCountriesFailure(String errMessage) {

    }

    @Override
    public void onMealsByCategorySuccess(ResponseMealInfoDto responseMealInfoDto) {

    }

    @Override
    public void onMealsByCategoryFailure(String errMessage) {

    }

    @Override
    public void onMealsByCountrySuccess(ResponseMealInfoDto responseMealInfoDto) {
        view.onSuccess(responseMealInfoDto);
    }

    @Override
    public void onMealsByCountryFailure(String errMessage) {
        view.onFailure(errMessage);
    }
}
