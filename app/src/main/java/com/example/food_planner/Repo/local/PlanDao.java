package com.example.food_planner.Repo.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.food_planner.model.dtos.PlanDto;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(PlanDto planDto);

    @Query("SELECT * FROM `plan` WHERE dayOfWeek = :day")
    Flowable<List<PlanDto>> getPlanByDay(int day);

    @Delete
    Completable deletePlanMeal(PlanDto planDto);

    @Query("DELETE FROM `plan` ")
    Completable deleteAllPlans();
}
