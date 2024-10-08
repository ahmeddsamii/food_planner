package com.example.food_planner.model.dtos;

import java.io.Serializable;

public class MealInfoDto implements Serializable {
    public String strMeal;
    public String strMealThumb;
    public String idMeal;

    public MealInfoDto(){

    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
}
