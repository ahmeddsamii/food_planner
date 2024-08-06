package com.example.food_planner.Repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;
@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(MealDto meal);

    @Update
    public void Update(MealDto meal);

    @Delete
    public void delete(MealDto meal);


    @Query("SELECT * FROM meal")
    LiveData<List<MealDto>> getAllMeals();




}
