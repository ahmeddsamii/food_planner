package com.example.food_planner.homePageScreen.presenter;

import com.example.food_planner.Repo.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.homePageScreen.view.AllCategoriesView;
import com.example.food_planner.homePageScreen.view.RandomMealView;
import com.example.food_planner.detailsMealScreen.view.AllIngredientsView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;
import com.example.food_planner.model.dtos.MealDto;

public class HomePresenter implements NetworkCallBack {
    RandomMealView view;
    AllCategoriesView categoriesView;
    AllIngredientsView ingredientsView;
    Repo repo;

    public HomePresenter(RandomMealView view, Repo repo,AllCategoriesView categoriesView){
        this.view = view;
        this.categoriesView = categoriesView;
        this.repo = repo;
    }

    public void getRandomMeal(){
        repo.getRandomMeal(this);
    }

    public  void getAllCategories(){repo.getAllCategories(this);}


    @Override
    public void onRandomMealSuccess(ResponseRandomMeal randomMeal) {
        view.onRandomMealSuccess(randomMeal);
    }

    @Override
    public void onRandomMealFailure(String errMessage) {
        view.onRandomMealFailure(errMessage);
    }

    @Override
    public void onAllCategoriesSuccess(ResponseCategory responseCategory) {
        categoriesView.onAllCategoriesSuccess(responseCategory);
    }

    @Override
    public void onAllCategoriesFailure(String errMessage) {
        categoriesView.onAllCategoriesFailure(errMessage);
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
}