package com.example.food_planner.Repo.network.api.callbacks;

import com.example.food_planner.model.dto_repos.ResponseCategory;

public interface CategoriesNetworkCallBack {
    void onAllCategoriesSuccess(ResponseCategory responseCategory);
    void onAllCategoriesFailure(String errMessage);

}
