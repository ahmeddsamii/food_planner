package com.example.food_planner.homePageScreen.presenter;

import com.example.food_planner.Repo.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealScreen.view.AllIngredientsView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;
import com.example.food_planner.model.dtos.MealDto;

public class IngredientPresenter implements NetworkCallBack {
    AllIngredientsView view;
    Repo repo;

    public IngredientPresenter(AllIngredientsView view,Repo repo){
        this.repo = repo;
        this.view = view;
    }


    public void getAllIngredients(){
        repo.getAllIngredients(this);
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
        view.onAllIngredientsSuccess(ingredients);
    }

    @Override
    public void onAllIngredientFailure(String errMessage) {
        view.onAllIngredientsFailure(errMessage);
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
}
