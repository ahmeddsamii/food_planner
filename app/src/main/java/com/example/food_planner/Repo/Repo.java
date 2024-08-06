package com.example.food_planner.Repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

import retrofit2.Call;

public class Repo {
    FirebaseDataSource firebaseDataSource;
    MealsRemoteDataSource mealsRemoteDataSource;
    MealsLocalDataSource mealsLocalDataSource;

    MealDao dao;
    private static Repo instance = null;

    private Repo(Context context){
        firebaseDataSource = FirebaseDataSource.getInstance();
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance();
        mealsLocalDataSource = MealsLocalDataSource.getInstance(context);
        dao = mealsLocalDataSource.mealDao();
    }

    public static Repo getInstance(Context context){
        if (instance == null){
            instance = new Repo(context);
        }
        return instance;
    }

    public FirebaseDataSource getFirebaseDataSource(){
        return firebaseDataSource;
    }

    public void getRandomMeal(NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForRandomMeal(callBack);
    }

    public void getAllCategories(NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForCategories(callBack);
    }

    public void getAllIngredients(NetworkCallBack networkCallBack){
        mealsRemoteDataSource.makeNetworkCallForIngredients(networkCallBack);
    }

    public void insert(MealDto mealDto){
        new Thread(){
            @Override
            public void run() {
                dao.insert(mealDto);
            }
        }.start();
    }

    public void getItemByName(NetworkCallBack callBack,String name){
        mealsRemoteDataSource.makeNetworkCallToGetItemByName(callBack, name);
    }

    public void delete(MealDto mealDto){
        new Thread(){
            @Override
            public void run() {
                dao.delete(mealDto);
            }
        }.start();

    }
    public LiveData<List<MealDto>> getLocalData(){
       return dao.getAllMeals();
    }

    public LiveData<List<MealDto>> getAllMeals(){
        return dao.getAllMeals();
    }

    public void getAllCountries(NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForAllCountries( callBack);
    }

    public void getMealsByCategory(String category, NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByCategory(category, callBack);
    }

    public void getMealsByCountry(String country, NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallToGetMealsByCountry(country,callBack);
    }



}
