//package com.example.food_planner.homePageScreen.presenter;
//
//import com.example.food_planner.Repo.network.NetworkCallBack;
//import com.example.food_planner.Repo.Repo;
//import com.example.food_planner.detailsMealScreen.view.IngredientsView;
//import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
//import com.example.food_planner.model.dto_repos.ResponseCategory;
//import com.example.food_planner.model.dto_repos.ResponseCountry;
//import com.example.food_planner.model.dto_repos.ResponseIngredient;
//import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
//import com.example.food_planner.model.dto_repos.ResponseMeals;
//import com.example.food_planner.model.dtos.MealDto;
//
//public class IngredientPresenter implements NetworkCallBack {
//    IngredientsView view;
//    Repo repo;
//
//    public IngredientPresenter(IngredientsView view, Repo repo) {
//        this.repo = repo;
//        this.view = view;
//    }
//
//    public void getIngredients() {
//        repo.getIngredient(this);
//    }
//
//    @Override
//    public void onRandomMealSuccess(ResponseMeals randomMeal) {
//
//    }
//
//    @Override
//    public void onRandomMealFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onAllCategoriesSuccess(ResponseCategory responseCategory) {
//
//    }
//
//    @Override
//    public void onAllCategoriesFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onIngredientSuccess(ResponseMeals ingredients) {
//        view.onAllIngredientsSuccess(ingredients);
//    }
//
//    @Override
//    public void onIngredientFailure(String errMessage) {
//        view.onAllIngredientsFailure(errMessage);
//    }
//
//    @Override
//    public void onItemByNameSuccess(MealDto mealDto) {
//
//    }
//
//    @Override
//    public void onItemByNameFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onAllCountriesSuccess(ResponseCountry countries) {
//
//    }
//
//    @Override
//    public void onAllCountriesFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onMealsByCategorySuccess(ResponseMealInfoDto responseMealInfoDto) {
//
//    }
//
//    @Override
//    public void onMealsByCategoryFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onMealsByCountrySuccess(ResponseMealInfoDto responseMealInfoDto) {
//
//    }
//
//    @Override
//    public void onMealsByCountryFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onSearchMealsByNameSuccess(ResponseMeals responseMeals) {
//
//    }
//
//    @Override
//    public void onSearchMealsByNameFailure(String errMessage) {
//
//    }
//
//    @Override
//    public void onAllIngredientSuccess(ResponseAllIngredients allIngredients) {
//
//    }
//
//    @Override
//    public void onAllIngredientsFailure(String errMessage) {
//
//    }
//}