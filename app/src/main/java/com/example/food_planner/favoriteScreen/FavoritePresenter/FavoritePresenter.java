package com.example.food_planner.favoriteScreen.FavoritePresenter;

import androidx.lifecycle.LiveData;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.favoriteScreen.view.FavoriteView;
import com.example.food_planner.favoriteScreen.view.onFavClickListener;
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public class FavoritePresenter implements onFavClickListener , FavoriteView {
    Repo repo;
    FavoriteView view;


    public FavoritePresenter(Repo repo, FavoriteView view){
        this.repo = repo;
        this.view = view;
    }


    public void insert(MealDto mealDto){
        new Thread(){
            @Override
            public void run() {
                repo.insert(mealDto);
            }
        }.start();
    }

    public void delete(MealDto mealDto){
        repo.delete(mealDto);
    }

    @Override
    public void onFavItemClicked(MealDto mealDto) {
        new Thread(){
            @Override
            public void run() {
                repo.insert(mealDto);
            }
        }.start();
    }

    @Override
    public void onFavItemDelete(MealDto mealDto) {
        repo.delete(mealDto);
        repo.getLocalData();
    }


    public void setData(LiveData<List<MealDto>> meals){
        view.getAllFavMeals(repo.getLocalData());
    }

    @Override
    public void getAllFavMeals(LiveData<List<MealDto>> meals) {

    }
}
