package com.example.food_planner.searchScreen.presenter;

import com.example.food_planner.Repo.NetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.searchScreen.view.CategorySearchView;
import com.example.food_planner.searchScreen.view.CountrySearchView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.searchScreen.view.MealsSearchByNameView;

public class SearchPresenter implements NetworkCallBack {
    Repo repo;
    CategorySearchView searchView;
    CountrySearchView countrySearchView;
    MealsSearchByNameView mealsSearchByNameView;

    public SearchPresenter(Repo repo, CategorySearchView searchView, CountrySearchView countrySearchView, MealsSearchByNameView mealsSearchByNameView){
        this.repo = repo;
        this.searchView = searchView;
        this.countrySearchView = countrySearchView;
        this.mealsSearchByNameView = mealsSearchByNameView;
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
        searchView.onCategorySearchViewSuccess(responseCategory.getCategories());
    }

    @Override
    public void onAllCategoriesFailure(String errMessage) {
        searchView.onCategorySearchViewFailure(errMessage);
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
        countrySearchView.onCountrySearchViewSuccess(countries);
    }

    @Override
    public void onAllCountriesFailure(String errMessage) {
        countrySearchView.onCountrySearchViewFailure(errMessage);
    }

    @Override
    public void onMealsByCategorySuccess(ResponseMealInfoDto responseMealInfoDto) {

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
        mealsSearchByNameView.onMealSearchByNameSuccess(responseMeals.getMeals());
    }

    @Override
    public void onSearchMealsByNameFailure(String errMessage) {
        mealsSearchByNameView.onMealSearchByNameFailure(errMessage);
    }
}
