package com.example.food_planner.Repo.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(MealDto meal);

    @Update
    void Update(MealDto meal);

    @Delete
    Completable delete(MealDto meal);


    @Query("SELECT * FROM meal")
    Flowable<List<MealDto>> getAllMeals();

    @Query("DELETE FROM meal")
    Completable deleteAllMeals();




}
