package com.example.food_planner.Repo;

import android.util.Log;

import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {
    private static MealsRemoteDataSource instance = null;
    MealService mealService;
    private static final String TAG = "MealsRemoteDataSource";

    private MealsRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        mealService = retrofit.create(MealService.class);
    }

    public static MealsRemoteDataSource getInstance(){
        if (instance == null){
            instance = new MealsRemoteDataSource();
        }
        return instance;
    }


    public void makeNetworkCallForRandomMeal(NetworkCallBack callBack){
        mealService.getRandomMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        callBack.onRandomMealSuccess(responseMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onRandomMealFailure(e.getMessage());
                    }
                });
    }


    public void makeNetworkCallForCategories(NetworkCallBack callBack){
        mealService.getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCategory>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: "+ d);
                    }

                    @Override
                    public void onSuccess(@NonNull ResponseCategory responseCategory) {
                        callBack.onAllCategoriesSuccess(responseCategory);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onAllCategoriesFailure(e.getMessage());
                    }
                });
    }


    public void makeNetworkCallToGetItemByName(NetworkCallBack callBack, String name) {
        mealService.getByName(name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        callBack.onItemByNameSuccess(responseMeals.getMeals().get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onItemByNameFailure(e.getMessage());
                    }
                });
    }

    public void makeNetworkCallForAllCountries(NetworkCallBack networkCallBack){
        mealService.getAllCountries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCountry>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onSuccess(@NonNull ResponseCountry country) {
                        networkCallBack.onAllCountriesSuccess(country);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkCallBack.onAllCountriesFailure(e.getMessage());
                    }
                });
    }




    public void makeNetworkCallForMealsByCategory(String category,NetworkCallBack callBack){
        mealService.getMealsByCategory(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                        callBack.onMealsByCategorySuccess(responseMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onMealsByCategoryFailure(e.getMessage());
                    }
                });
    }


    public void makeNetworkCallToGetMealsByCountry(String country, NetworkCallBack callBack){
        mealService.getMealsByCountry(country).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMealInfoDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMealInfoDto responseMealInfoDto) {
                        callBack.onMealsByCountrySuccess(responseMealInfoDto);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onMealsByCountryFailure(e.getMessage());
                    }
                });
    }


    public void makeNetworkCallForMealsByName(String name, NetworkCallBack networkCallBack){
        mealService.searchMealsByName(name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMeals>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMeals responseMeals) {
                    networkCallBack.onSearchMealsByNameSuccess(responseMeals);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkCallBack.onSearchMealsByNameFailure(e.getMessage());
                    }
                });
    }


    public void makeCallForAllIngredients(NetworkCallBack callBack){
        mealService.getAllIngredient().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseAllIngredients>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseAllIngredients allIngredients) {
                        callBack.onAllIngredientSuccess(allIngredients);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onAllIngredientsFailure(e.getMessage());
                    }
                });
    }



    public void makeNetworkCallForMealsByIngredients(String ingredient, NetworkCallBack callBack){
        mealService.getMealsByIngredients(ingredient).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseMealByIngredientDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResponseMealByIngredientDto responseMealByIngredientDto) {
                        callBack.onAllMealsByIngredientsSuccess(responseMealByIngredientDto);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onAllMealsByIngredientsFailure(e.getMessage());
                    }
                });
    }


}
