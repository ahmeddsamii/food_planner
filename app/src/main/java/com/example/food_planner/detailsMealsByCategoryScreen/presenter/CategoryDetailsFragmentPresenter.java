package com.example.food_planner.detailsMealsByCategoryScreen.presenter;


import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCategoryScreen.view.CategoryDetailsView;
import com.example.food_planner.detailsMealsByCategoryScreen.view.onMealByNameView;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryDetailsFragmentPresenter {
    Repo repo;
    onMealByNameView onMealByNameView;

    CategoryDetailsView view;


    public CategoryDetailsFragmentPresenter(Repo repo, CategoryDetailsView view, onMealByNameView onMealByNameView) {
        this.repo = repo;
        this.view = view;
        this.onMealByNameView = onMealByNameView;
    }

    public void getMealByName(String MealName) {
        repo.getItemByName(MealName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        onMealByNameView.onMealByNameSuccess(responseMeals.getMeals().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onMealByNameView.onMealByNameFailure(e.getMessage());
                    }
                });
    }


    public void getAllMealsByCategory(String category) {
        repo.getMealsByCategory(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        view.onSuccess(responseMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFailure(e.getMessage());
                    }
                });
    }


}
