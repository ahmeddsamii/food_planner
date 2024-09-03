package com.example.food_planner.detailsMealsByCountryScreen.presenter;

import com.example.food_planner.Repo.network.api.callbacks.ItemByNameNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByCountryNetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCountryScreen.view.onMealsByCountryView;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsByCountryPresenter  {

    Repo repo;
    onMealsByCountryView view;

    public MealsByCountryPresenter(Repo repo, onMealsByCountryView view) {
        this.repo = repo;
        this.view = view;
    }

    public void getMealByName(String mealName) {
        repo.getItemByName(mealName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        view.onItemByNameSuccess(responseMeals.getMeals().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFailure(e.getMessage());
                    }
                });
    }

    public void getMealsByCountry(String country) {
        repo.getMealsByCountry(country).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMealInfoDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMealInfoDto responseMealInfoDto) {
                        view.onSuccess(responseMealInfoDto);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFailure(e.getMessage());
                    }
                });
    }



}
