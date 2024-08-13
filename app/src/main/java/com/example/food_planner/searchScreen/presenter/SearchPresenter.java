package com.example.food_planner.searchScreen.presenter;

import com.example.food_planner.Repo.network.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.searchScreen.view.AllIngredientsSearchView;
import com.example.food_planner.searchScreen.view.CategorySearchView;
import com.example.food_planner.searchScreen.view.CountrySearchView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.searchScreen.view.MealsSearchByNameView;

public class SearchPresenter implements NetworkCallBack {
    Repo repo;
    CategorySearchView categorySearchView;
    CountrySearchView countrySearchView;
    MealsSearchByNameView mealsSearchByNameView;
    AllIngredientsSearchView allIngredientsSearchView;

    public SearchPresenter(Repo repo, CategorySearchView categorySearchView, CountrySearchView countrySearchView, MealsSearchByNameView mealsSearchByNameView , AllIngredientsSearchView allIngredientsSearchView){
        this.repo = repo;
        this.categorySearchView = categorySearchView;
        this.countrySearchView = countrySearchView;
        this.mealsSearchByNameView = mealsSearchByNameView;
        this.allIngredientsSearchView = allIngredientsSearchView;
    }


    public void getAllCategoriesSearchItems(){
         repo.getAllCategories(this);
    }

    public void getAllCountriesSearchItems(){
        repo.getAllCountries(this);
    }

    public void getMealSearchByName(String name){
        repo.getSearchMealsByName(name,this);
    }

    @Override
    public void onRandomMealSuccess(ResponseMeals randomMeal) {

    }

    @Override
    public void onRandomMealFailure(String errMessage) {

    }

    @Override
    public void onAllCategoriesSuccess(ResponseCategory responseCategory) {
        categorySearchView.onCategorySearchViewSuccess(responseCategory.getCategories());
    }

    @Override
    public void onAllCategoriesFailure(String errMessage) {
        categorySearchView.onCategorySearchViewFailure(errMessage);
    }

    @Override
    public void onIngredientSuccess(ResponseMeals ingredients) {

    }

    @Override
    public void onIngredientFailure(String errMessage) {

    }

    @Override
    public void onItemByNameSuccess(MealDto mealDto) {

    }

    @Override
    public void onItemByNameFailure(String errMessage) {

    }

    @Override
    public void onAllCountriesSuccess(ResponseCountry countries) {
        countrySearchView.onCountrySearchViewSuccess(countries);
    }

    @Override
    public void onAllCountriesFailure(String errMessage) {
        countrySearchView.onCountrySearchViewFailure(errMessage);
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
        if (responseMeals != null && responseMeals.getMeals() != null) {
            mealsSearchByNameView.onMealSearchByNameSuccess(responseMeals.getMeals());
        } else {
            mealsSearchByNameView.onMealSearchByNameFailure("No meals found");
        }
    }

    public void getAllIngredients(){
        repo.getAllIngredients(this);
    }

    @Override
    public void onSearchMealsByNameFailure(String errMessage) {
        mealsSearchByNameView.onMealSearchByNameFailure(errMessage != null ? errMessage : "Unknown error occurred");
    }

    @Override
    public void onAllIngredientSuccess(ResponseAllIngredients allIngredients) {
        allIngredientsSearchView.onAllIngredientsSuccess(allIngredients);
    }

    @Override
    public void onAllIngredientsFailure(String errMessage) {
        allIngredientsSearchView.onAllIngredientsFailure(errMessage);
    }

    @Override
    public void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto meals) {

    }

    @Override
    public void onAllMealsByIngredientsFailure(String errMessage) {

    }
}
