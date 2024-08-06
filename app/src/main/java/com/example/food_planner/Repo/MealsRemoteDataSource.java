package com.example.food_planner.Repo;

import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {
    private static MealsRemoteDataSource instance = null;
    MealService mealService;

    private MealsRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
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
        mealService.getRandomMeal().enqueue(new Callback<ResponseRandomMeal>() {
            @Override
            public void onResponse(Call<ResponseRandomMeal> call, Response<ResponseRandomMeal> response) {
                callBack.onRandomMealSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseRandomMeal> call, Throwable throwable) {
                callBack.onRandomMealFailure(throwable.getMessage());
            }
        });

    }


    public void makeNetworkCallForCategories(NetworkCallBack callBack){
        mealService.getAllCategories().enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                callBack.onAllCategoriesSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable throwable) {
                callBack.onAllCategoriesFailure(throwable.getMessage());
            }
        });
    }


    public void makeNetworkCallForIngredients(NetworkCallBack networkCallBack){
        mealService.getAllIngredients().enqueue(new Callback<ResponseIngredient>() {
            @Override
            public void onResponse(Call<ResponseIngredient> call, Response<ResponseIngredient> response) {
                networkCallBack.onAllIngredientsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseIngredient> call, Throwable throwable) {
                networkCallBack.onAllIngredientFailure(throwable.getMessage());
            }
        });
    }

    public void makeNetworkCallToGetItemByName(NetworkCallBack callBack, String name) {
        mealService.getByName(name).enqueue(new Callback<ResponseRandomMeal>() {
            @Override
            public void onResponse(Call<ResponseRandomMeal> call, Response<ResponseRandomMeal> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null && !response.body().getMeals().isEmpty()) {
                    callBack.onItemByNameSuccess(response.body().getMeals().get(0));
                } else {
                    callBack.onItemByNameFailure("No meals found");
                }
            }

            @Override
            public void onFailure(Call<ResponseRandomMeal> call, Throwable throwable) {
                callBack.onItemByNameFailure(throwable.getMessage());
            }
        });
    }

    public void makeNetworkCallForAllCountries(NetworkCallBack networkCallBack){
        mealService.getAllCountries().enqueue(new Callback<ResponseCountry>() {
            @Override
            public void onResponse(Call<ResponseCountry> call, Response<ResponseCountry> response) {
                networkCallBack.onAllCountriesSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseCountry> call, Throwable throwable) {
                networkCallBack.onAllCountriesFailure(throwable.getMessage());
            }
        });
    }


    public void makeNetworkCallForMealsByCategory(String category,NetworkCallBack callBack){
        mealService.getMealsByCategory(category).enqueue(new Callback<ResponseMealInfoDto>() {
            @Override
            public void onResponse(Call<ResponseMealInfoDto> call, Response<ResponseMealInfoDto> response) {
                callBack.onMealsByCategorySuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseMealInfoDto> call, Throwable throwable) {
                callBack.onMealsByCategoryFailure(throwable.getMessage());
            }
        });
    }


    public void makeNetworkCallToGetMealsByCountry(String country, NetworkCallBack callBack){
        mealService.getMealsByCountry(country).enqueue(new Callback<ResponseMealInfoDto>() {
            @Override
            public void onResponse(Call<ResponseMealInfoDto> call, Response<ResponseMealInfoDto> response) {
                callBack.onMealsByCountrySuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseMealInfoDto> call, Throwable throwable) {
                callBack.onMealsByCountryFailure(throwable.getMessage());
            }
        });
    }



}
