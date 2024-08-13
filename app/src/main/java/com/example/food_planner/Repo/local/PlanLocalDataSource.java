package com.example.food_planner.Repo.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.food_planner.model.dtos.PlanDto;

@Database(entities = {PlanDto.class}, version = 1)
public abstract class PlanLocalDataSource extends RoomDatabase {
    private static PlanLocalDataSource instance = null;
    public abstract PlanDao planDao();
    public static PlanLocalDataSource getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PlanLocalDataSource.class, "plan.db").build();
        }
        return instance;
    }
}
