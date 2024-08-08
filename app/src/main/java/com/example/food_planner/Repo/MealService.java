package com.example.food_planner.Repo;

import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    Call<ResponseMeals> getRandomMeal();

    @GET("categories.php")
    Call<ResponseCategory> getAllCategories();


    @GET("search.php")
    Call<ResponseMeals> getByName(@Query("s") String name);


    @GET("list.php?a=list")
    Call<ResponseCountry> getAllCountries();

    @GET("filter.php")
    Call<ResponseMealInfoDto> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<ResponseMealInfoDto> getMealsByCountry(@Query("a") String country);

    @GET("search.php")
    Call<ResponseMeals> searchMealsByName(@Query("s") String name);

    @GET("list.php?i=list")
    Call<ResponseAllIngredients> getAllIngredient();

    @GET("filter.php")
    Call<ResponseMealByIngredientDto> getMealsByIngredients(@Query("i") String ingredient);


}
