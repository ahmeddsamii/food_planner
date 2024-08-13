package com.example.food_planner.Repo.network;

import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    Single<ResponseMeals> getRandomMeal();

    @GET("categories.php")
    Single<ResponseCategory> getAllCategories();


    @GET("search.php")
    Single<ResponseMeals> getByName(@Query("s") String name);


    @GET("list.php?a=list")
    Single<ResponseCountry> getAllCountries();

    @GET("filter.php")
    Single<ResponseMeals> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<ResponseMealInfoDto> getMealsByCountry(@Query("a") String country);

    @GET("search.php")
    Single<ResponseMeals> searchMealsByName(@Query("s") String name);

    @GET("list.php?i=list")
    Single<ResponseAllIngredients> getAllIngredient();

    @GET("filter.php")
    Single<ResponseMealByIngredientDto> getMealsByIngredients(@Query("i") String ingredient);


}
