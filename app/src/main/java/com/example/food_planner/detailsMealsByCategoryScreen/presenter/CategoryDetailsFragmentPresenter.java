package com.example.food_planner.detailsMealsByCategoryScreen.presenter;

import com.example.food_planner.Repo.network.api.callbacks.ItemByNameNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByCategoriesNetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCategoryScreen.view.CategoryDetailsView;
import com.example.food_planner.detailsMealsByCategoryScreen.view.onMealByNameView;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;

public class CategoryDetailsFragmentPresenter implements   ItemByNameNetworkCallBack, MealsByCategoriesNetworkCallBack {
    Repo repo;
    onMealByNameView onMealByNameView;

    CategoryDetailsView view;


    public CategoryDetailsFragmentPresenter(Repo repo, CategoryDetailsView view , onMealByNameView onMealByNameView){
        this.repo = repo;
        this.view = view;
        this.onMealByNameView = onMealByNameView;
    }

    public void getMealByName(String MealName){
        repo.getItemByName(this,MealName);
    }


    public void getAllMealsByCategory(String category){
        repo.getMealsByCategory(category, this);
    }





    @Override
    public void onItemByNameSuccess(MealDto mealDto) {
        onMealByNameView.onMealByNameSuccess(mealDto);

    }

    @Override
    public void onItemByNameFailure(String errMessage) {
        onMealByNameView.onMealByNameFailure(errMessage);
    }



    @Override
    public void onMealsByCategorySuccess(ResponseMeals responseMealInfoDto) {
        view.onSuccess(responseMealInfoDto);
    }

    @Override
    public void onMealsByCategoryFailure(String errMessage) {
        view.onFailure(errMessage);
    }








}
