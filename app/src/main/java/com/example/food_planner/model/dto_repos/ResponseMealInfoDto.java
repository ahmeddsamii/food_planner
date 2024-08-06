package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.MealInfoDto;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseMealInfoDto {
    @SerializedName("meals")
    public List<MealInfoDto> mealInfoList;

    public ResponseMealInfoDto() {
    }

    public ResponseMealInfoDto(ArrayList<MealInfoDto> mealInfoList) {
        this.mealInfoList = mealInfoList;
    }

    public List<MealInfoDto> getMealInfoList() {
        return mealInfoList;
    }

    public void setMealInfoList(List<MealInfoDto> mealInfoList) {
        this.mealInfoList = mealInfoList;
    }
}
