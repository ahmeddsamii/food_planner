package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseCountry;

public interface AllCountriesNetworkCallBack {
    void onAllCountriesSuccess(ResponseCountry countries);
    void onAllCountriesFailure(String errMessage);
}
