package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.CountryDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCountry {
    @SerializedName("meals")
    List<CountryDto> countries;

    public ResponseCountry() {
    }

    public ResponseCountry(List<CountryDto> countries) {
        this.countries = countries;
    }

    public List<CountryDto> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }
}
