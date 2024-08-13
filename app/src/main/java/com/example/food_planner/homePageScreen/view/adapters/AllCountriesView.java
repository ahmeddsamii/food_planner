package com.example.food_planner.homePageScreen.view.adapters;

import com.example.food_planner.model.dto_repos.ResponseCountry;

public interface AllCountriesView {
    void onAllCountriesSuccess(ResponseCountry countries);
    void onAllCountriesFailure(String errMessage);
}
