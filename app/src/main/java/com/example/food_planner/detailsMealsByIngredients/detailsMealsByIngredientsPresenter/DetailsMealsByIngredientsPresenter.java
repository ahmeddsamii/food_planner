package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsPresenter;

import com.example.food_planner.Repo.network.api.callbacks.ItemByNameNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByIngredientNetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView.DetailsMealsByIngredientsView;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dtos.MealDto;

public class DetailsMealsByIngredientsPresenter implements  ItemByNameNetworkCallBack, MealsByIngredientNetworkCallBack {
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
    public void onItemByNameSuccess(MealDto mealDto) {
        detailsMealsByIngredientsView.onItemByNameSuccess(mealDto);
    }

    @Override
    public void onItemByNameFailure(String errMessage) {
        detailsMealsByIngredientsView.onItemByNameFailure(errMessage);
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
