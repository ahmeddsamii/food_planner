package com.example.food_planner.model.dtos;


import java.io.Serializable;
import java.util.Map;

public class MealData implements Serializable {
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private Map<String, String> ingredients;
    private Map<String, String> measures;

    // No-argument constructor required for Firestore
    public MealData() {}

    // Constructor, getters, and setters

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    // ... (add getters and setters for all fields)

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<String, String> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, String> measures) {
        this.measures = measures;
    }
}
