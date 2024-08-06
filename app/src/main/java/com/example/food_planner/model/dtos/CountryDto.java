package com.example.food_planner.model.dtos;

import java.io.Serializable;

public class CountryDto implements Serializable {
    public String strArea;
    public CountryDto(){

    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
