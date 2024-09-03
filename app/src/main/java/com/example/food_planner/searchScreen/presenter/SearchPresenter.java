package com.example.food_planner.searchScreen.presenter;

import android.util.Log;


import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.searchScreen.view.AllIngredientsSearchView;
import com.example.food_planner.searchScreen.view.CategorySearchView;
import com.example.food_planner.searchScreen.view.CountrySearchView;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.searchScreen.view.MealsSearchByNameView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    Repo repo;
    CategorySearchView categorySearchView;
    CountrySearchView countrySearchView;
    MealsSearchByNameView mealsSearchByNameView;
    AllIngredientsSearchView allIngredientsSearchView;
    private static final String TAG = "SearchPresenter";

    public SearchPresenter(Repo repo, CategorySearchView categorySearchView, CountrySearchView countrySearchView, MealsSearchByNameView mealsSearchByNameView, AllIngredientsSearchView allIngredientsSearchView) {
        this.repo = repo;
        this.categorySearchView = categorySearchView;
        this.countrySearchView = countrySearchView;
        this.mealsSearchByNameView = mealsSearchByNameView;
        this.allIngredientsSearchView = allIngredientsSearchView;
    }


    public void getAllCategoriesSearchItems() {
        repo.getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCategory>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: " + d);
                    }

                    @Override
                    public void onSuccess(@NonNull ResponseCategory responseCategory) {
                        categorySearchView.onCategorySearchViewSuccess(responseCategory.getCategories());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        categorySearchView.onCategorySearchViewFailure(e.getMessage());
                    }
                });
    }

    public void getAllCountriesSearchItems() {
        repo.getAllCountries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCountry>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseCountry country) {
                        countrySearchView.onCountrySearchViewSuccess(country);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countrySearchView.onCountrySearchViewFailure(e.getMessage());
                    }
                });
    }

    public void getMealSearchByName(String name) {
        repo.getSearchMealsByName(name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        mealsSearchByNameView.onMealSearchByNameSuccess(responseMeals.getMeals());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mealsSearchByNameView.onMealSearchByNameFailure(e.getMessage());
                    }
                });
    }

    public void getAllIngredients() {
        repo.getAllIngredients().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseAllIngredients>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseAllIngredients allIngredients) {
                        allIngredientsSearchView.onAllIngredientsSuccess(allIngredients);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        allIngredientsSearchView.onAllIngredientsFailure(e.getMessage());
                    }
                });
    }



}
