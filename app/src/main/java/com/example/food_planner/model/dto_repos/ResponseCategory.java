package com.example.food_planner.model.dto_repos;

import com.example.food_planner.model.dtos.CategoryDto;

import java.util.List;

public class ResponseCategory {
    List<CategoryDto> categories;

    public ResponseCategory() {
    }

    public ResponseCategory(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
