package com.example.food_planner.detailsMealsByCountryScreen.presenter;

import com.example.food_planner.Repo.network.api.callbacks.ItemByNameNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByCountryNetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCountryScreen.view.onMealsByCountryView;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dtos.MealDto;

public class MealsByCountryPresenter implements  ItemByNameNetworkCallBack, MealsByCountryNetworkCallBack {

    Repo repo;
    onMealsByCountryView view;

    public MealsByCountryPresenter(Repo repo, onMealsByCountryView view){
        this.repo = repo;
        this.view = view;
    }

    public void getMealByName(String mealName){
        repo.getItemByName(this,mealName);
    }

    public void getMealsByCountry(String country){
        repo.getMealsByCountry(country, this);
    }



    @Override
    public void onItemByNameSuccess(MealDto mealDto) {
        view.onItemByNameSuccess(mealDto);
    }

    @Override
    public void onItemByNameFailure(String errMessage) {

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
