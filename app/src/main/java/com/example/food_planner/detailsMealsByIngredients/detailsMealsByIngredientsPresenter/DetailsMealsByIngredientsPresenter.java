package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsPresenter;


import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView.DetailsMealsByIngredientsView;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsMealsByIngredientsPresenter {
    Repo repo;
    DetailsMealsByIngredientsView detailsMealsByIngredientsView;

    public DetailsMealsByIngredientsPresenter(Repo repo, DetailsMealsByIngredientsView detailsMealsByIngredientsView){
        this.detailsMealsByIngredientsView = detailsMealsByIngredientsView;
        this.repo = repo;
    }



    public void getAllMealsByIngredients(String ingredient){
        repo.getAllMealsByIngredients(ingredient).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMealByIngredientDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMealByIngredientDto responseMealByIngredientDto) {
                        detailsMealsByIngredientsView.onAllMealsByIngredientsSuccess(responseMealByIngredientDto);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailsMealsByIngredientsView.onAllMealsByIngredientsFailure(e.getMessage());
                    }
                });
    }

    public void getMealByName(String mealName){
        repo.getItemByName(mealName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        detailsMealsByIngredientsView.onItemByNameSuccess(responseMeals.getMeals().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailsMealsByIngredientsView.onItemByNameFailure(e.getMessage());
                    }
                });
    }

}
