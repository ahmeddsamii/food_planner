package com.example.food_planner.homePageScreen.presenter;

import com.example.food_planner.Repo.network.api.callbacks.AllCountriesNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.CategoriesNetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.Repo.network.api.callbacks.RandomMealCallBack;
import com.example.food_planner.homePageScreen.view.AllCategoriesView;
import com.example.food_planner.homePageScreen.view.RandomMealView;
import com.example.food_planner.homePageScreen.view.adapters.AllCountriesView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMeals;

public class HomePresenter implements CategoriesNetworkCallBack, RandomMealCallBack, AllCountriesNetworkCallBack {
    RandomMealView randomView;
    AllCategoriesView categoriesView;
    AllCountriesView allCountriesView;

    Repo repo;

    public HomePresenter(RandomMealView view, Repo repo,AllCategoriesView categoriesView, AllCountriesView allCountriesView){
        this.randomView = view;
        this.categoriesView = categoriesView;
        this.repo = repo;
        this.allCountriesView = allCountriesView;
    }

    public void getRandomMeal(){
        repo.getRandomMeal(this);
    }


    public  void getAllCategories(){
        repo.getAllCategories(this);
    }
    public void getAllCountries(){repo.getAllCountries(this);}


    @Override
    public void onRandomMealSuccess(ResponseMeals randomMeal) {
        randomView.onRandomMealSuccess(randomMeal);
    }

    @Override
    public void onRandomMealFailure(String errMessage) {
        randomView.onRandomMealFailure(errMessage);
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
    public void onAllCountriesSuccess(ResponseCountry countries) {
        allCountriesView.onAllCountriesSuccess(countries);
    }

    @Override
    public void onAllCountriesFailure(String errMessage) {
        allCountriesView.onAllCountriesFailure(errMessage);
    }


}
