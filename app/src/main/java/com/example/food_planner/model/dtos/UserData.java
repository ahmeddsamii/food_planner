package com.example.food_planner.model.dtos;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {
    private List<MealData> meals;


    public UserData() {}

    public UserData(List<MealData> meals) {
        this.meals = meals;
    }

    public List<MealData> getMeals() {
        return meals;
    }

    public void setMeals(List<MealData> meals) {
        this.meals = meals;
    }
}
