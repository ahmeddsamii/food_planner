package com.example.food_planner.homePageScreen.presenter;

import android.util.Log;

import com.example.food_planner.Repo.network.api.callbacks.AllCountriesNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.CategoriesNetworkCallBack;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.Repo.network.api.callbacks.RandomMealCallBack;
import com.example.food_planner.homePageScreen.view.AllCategoriesView;
import com.example.food_planner.homePageScreen.view.RandomMealView;
import com.example.food_planner.homePageScreen.view.AllCountriesView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter{
    RandomMealView randomView;
    AllCategoriesView categoriesView;
    AllCountriesView allCountriesView;
    private static final String TAG = "HomePresenter";

    Repo repo;

    public HomePresenter(RandomMealView view, Repo repo,AllCategoriesView categoriesView, AllCountriesView allCountriesView){
        this.randomView = view;
        this.categoriesView = categoriesView;
        this.repo = repo;
        this.allCountriesView = allCountriesView;
    }

    public void getRandomMeal(){
        repo.getRandomMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        randomView.onRandomMealSuccess(responseMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        randomView.onRandomMealFailure(e.getMessage());
                    }
                });
    }


    public  void getAllCategories(){
        repo.getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCategory>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: "+ d);
                    }

                    @Override
                    public void onSuccess(@NonNull ResponseCategory responseCategory) {
                        categoriesView.onAllCategoriesSuccess(responseCategory);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        categoriesView.onAllCategoriesFailure(e.getMessage());
                    }
                });
    }
    public void getAllCountries(){
        repo.getAllCountries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCountry>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onSuccess(@NonNull ResponseCountry country) {
                        allCountriesView.onAllCountriesSuccess(country);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        allCountriesView.onAllCountriesFailure(e.getMessage());
                    }
                });
    }


}
