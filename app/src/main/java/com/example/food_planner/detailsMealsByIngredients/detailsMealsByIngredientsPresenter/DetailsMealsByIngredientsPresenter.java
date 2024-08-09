package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsPresenter;

import com.example.food_planner.Repo.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView.DetailsMealsByIngredientsView;
import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;

public class DetailsMealsByIngredientsPresenter implements NetworkCallBack {
    Repo repo;
    DetailsMealsByIngredientsView detailsMealsByIngredientsView;

    public DetailsMealsByIngredientsPresenter(Repo repo, DetailsMealsByIngredientsView detailsMealsByIngredientsView){
        this.detailsMealsByIngredientsView = detailsMealsByIngredientsView;
        this.repo = repo;
    }



    public void getAllMealsByIngredients(String ingredient){
        repo.getAllMealsByIngredients(ingredient, this);
    }

    public void getMealByName(String mealName){
        repo.getItemByName(this, mealName);
    }

    @Override
    public void onRandomMealSuccess(ResponseMeals randomMeal) {

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
    public void onIngredientSuccess(ResponseMeals ingredients) {

    }

    @Override
    public void onIngredientFailure(String errMessage) {

    }

    @Override
    public void onItemByNameSuccess(MealDto mealDto) {
        detailsMealsByIngredientsView.onItemByNameSuccess(mealDto);
    }

    @Override
    public void onItemByNameFailure(String errMessage) {
        detailsMealsByIngredientsView.onItemByNameFailure(errMessage);
    }

    @Override
    public void onAllCountriesSuccess(ResponseCountry countries) {

    }

    @Override
    public void onAllCountriesFailure(String errMessage) {

    }

    @Override
    public void onMealsByCategorySuccess(ResponseMeals responseMealInfoDto) {

    }

    @Override
    public void onMealsByCategoryFailure(String errMessage) {

    }

    @Override
    public void onMealsByCountrySuccess(ResponseMealInfoDto responseMealInfoDto) {

    }

    @Override
    public void onMealsByCountryFailure(String errMessage) {

    }

    @Override
    public void onSearchMealsByNameSuccess(ResponseMeals responseMeals) {

    }

    @Override
    public void onSearchMealsByNameFailure(String errMessage) {

    }

    @Override
    public void onAllIngredientSuccess(ResponseAllIngredients allIngredients) {

    }

    @Override
    public void onAllIngredientsFailure(String errMessage) {

    }

    @Override
    public void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto meals) {
        detailsMealsByIngredientsView.onAllMealsByIngredientsSuccess(meals);
    }

    @Override
    public void onAllMealsByIngredientsFailure(String errMessage) {
        detailsMealsByIngredientsView.onAllMealsByIngredientsFailure(errMessage);
    }
}
