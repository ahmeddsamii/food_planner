package com.example.food_planner.Repo.network.api;


import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {
    private static MealsRemoteDataSource instance = null;
    MealService mealService;

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


    public Single<ResponseMeals> makeNetworkCallForRandomMeal(){
        return mealService.getRandomMeal();
    }


    public Single<ResponseCategory> makeNetworkCallForCategories(){
        return mealService.getAllCategories();
    }


    public Single<ResponseMeals> makeNetworkCallToGetItemByName(String name) {
        return mealService.getByName(name);
    }

    public Single<ResponseCountry> makeNetworkCallForAllCountries(){
        return mealService.getAllCountries();
    }




    public Single<ResponseMeals> makeNetworkCallForMealsByCategory(String category){
       return mealService.getMealsByCategory(category);
    }


    public Single<ResponseMealInfoDto> makeNetworkCallToGetMealsByCountry(String country){
        return mealService.getMealsByCountry(country);
    }


    public Single<ResponseMeals> makeNetworkCallForMealsByName(String name){
        return mealService.searchMealsByName(name);
    }


    public Single<ResponseAllIngredients> makeCallForAllIngredients(){
        return mealService.getAllIngredient();
    }



    public Single<ResponseMealByIngredientDto> makeNetworkCallForMealsByIngredients(String ingredient){
        return mealService.getMealsByIngredients(ingredient);
    }


}
