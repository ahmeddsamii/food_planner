package com.example.food_planner.searchScreen.view;

import com.example.food_planner.model.dtos.CategoryDto;

import java.util.List;

public interface CategorySearchView {
    void onCategorySearchViewSuccess(List<CategoryDto> categories);
    void onCategorySearchViewFailure(String errMessage);
}
