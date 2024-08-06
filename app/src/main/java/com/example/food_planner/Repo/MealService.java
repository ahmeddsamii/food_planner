package com.example.food_planner.Repo;

import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;
import com.example.food_planner.model.dtos.MealDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    Call<ResponseRandomMeal> getRandomMeal();

    @GET("categories.php")
    Call<ResponseCategory> getAllCategories();

    @GET("random.php")
    Call<ResponseIngredient> getAllIngredients();
    @GET("search.php")
    Call<ResponseRandomMeal> getByName(@Query("s") String name);


    @GET("list.php?a=list")
    Call<ResponseCountry> getAllCountries();

    @GET("filter.php")
    Call<ResponseMealInfoDto> getMealsByCategory(@Query("c") String category);
}
