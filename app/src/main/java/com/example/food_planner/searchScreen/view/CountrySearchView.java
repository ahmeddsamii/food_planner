package com.example.food_planner.searchScreen.view;

import com.example.food_planner.model.dto_repos.ResponseCountry;

public interface CountrySearchView {
    void onCountrySearchViewSuccess(ResponseCountry countries);
    void onCountrySearchViewFailure(String errMessage);
}
