package com.example.food_planner.detailsMealsByCategoryScreen.presenter;

import com.example.food_planner.Repo.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCategoryScreen.view.CategoryDetailsView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;
import com.example.food_planner.model.dtos.MealDto;

public class CategoryDetailsFragmentPresenter implements NetworkCallBack {
    Repo repo;

    CategoryDetailsView view;


    public CategoryDetailsFragmentPresenter(Repo repo, CategoryDetailsView view){
        this.repo = repo;
        this.view = view;
    }

    public void getMealByName(String MealName){
        repo.getItemByName(this,MealName);
    }


    public void getAllMealsByCategory(String category){
        repo.getMealsByCategory(category, this);
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
        view.onSuccess(responseMealInfoDto);
    }

    @Override
    public void onMealsByCategoryFailure(String errMessage) {
        view.onFailure(errMessage);
    }

    @Override
    public void onMealsByCountrySuccess(ResponseMealInfoDto responseMealInfoDto) {

    }

    @Override
    public void onMealsByCountryFailure(String errMessage) {

    }
}
