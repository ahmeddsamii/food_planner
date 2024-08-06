package com.example.food_planner.Repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.food_planner.model.dtos.MealDto;

@Database(entities = {MealDto.class}, version = 1)
public  abstract class MealsLocalDataSource extends RoomDatabase {
    private static MealsLocalDataSource instance = null;

    public abstract MealDao mealDao();

    public static MealsLocalDataSource getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MealsLocalDataSource.class,"FavoriteMealsDatabase").build();
        }
        return instance;
    }


}
